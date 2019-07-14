package com.example.community.community.controller;
import com.example.community.community.Service.UserService;
import com.example.community.community.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginRegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String toLogin(){
        return "login";
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
}
