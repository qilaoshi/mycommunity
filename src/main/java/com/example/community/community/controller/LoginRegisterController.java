package com.example.community.community.controller;
import com.example.community.community.Service.UserService;
import com.example.community.community.config.MailService;
import com.example.community.community.dto.CommonJsonDto;
import com.example.community.community.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginRegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private AmqpAdmin amqpAdmin;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MailService mailService;

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }
    @GetMapping("/register")
    public String toRegister(){
        return "register";
    }

    @PostMapping("/do_register")
    @ResponseBody
    public Object doRegister( @RequestBody User user, Model model){
        CommonJsonDto commonJsonDto=new CommonJsonDto();
        ValueOperations<String,String> operations=stringRedisTemplate.opsForValue();
        System.out.println(operations.get(user.getEmail())+"验证码"+user.getNumbers());
        if (!user.getNumbers().equals(operations.get(user.getEmail()))){
            commonJsonDto.setCode(-1);
            commonJsonDto.setMsg("验证码错误");
            return commonJsonDto;
        }
        User user1=userService.selectUser(user.getUsername());
        if (user1==null){
            commonJsonDto.setCode(0);
            commonJsonDto.setMsg("注册成功");
            userService.register(user);
            stringRedisTemplate.delete(user.getEmail());
            return commonJsonDto;
        }
        else{
            commonJsonDto.setCode(-1);
            commonJsonDto.setMsg("该用户名已经存在");
            return commonJsonDto;
        }
    }

    @PostMapping("/do_login")
    public String doLogin(User user, Model model, HttpServletResponse response, HttpServletRequest request){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(usernamePasswordToken);
            User user1= userService.selectUser(user.getUsername());
            String token = UUID.randomUUID().toString();
            user1.setToken(token);
            userService.insertToken(user1);
            Cookie cookie=new Cookie("token",token);
            cookie.setMaxAge(4320 * 60);
            response.addCookie(cookie);
            return "redirect:/";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @GetMapping("/sendMail")
    @ResponseBody
    public Object sendMail(HttpServletRequest request,String email){
        System.out.println(email);
        int random=(int)Math.round(Math.random() * 10000);
        String numbers=String.valueOf(random);
        ValueOperations<String,String> operations=stringRedisTemplate.opsForValue();
        operations.set(email,numbers);
        mailService.sendSimpleMail("1529599467@qq.com",email,"1529599467@qq.com","验证码","验证码是"+numbers);
        CommonJsonDto commonJsonDto=new CommonJsonDto();
        commonJsonDto.setCode(0);
        commonJsonDto.setMsg("已发送");
        return commonJsonDto;
    }

}
