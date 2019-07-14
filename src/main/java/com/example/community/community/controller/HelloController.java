package com.example.community.community.controller;

import com.example.community.community.Service.NotificationService;
import com.example.community.community.Service.PublishService;
import com.example.community.community.Service.UserService;
import com.example.community.community.mapper.PublishMapper;
import com.example.community.community.model.Publish;
import com.example.community.community.model.User;
import com.example.community.community.model.UserWithPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private UserService userService;
    @Autowired
    private PublishService publishService;
    @Autowired
    private PublishMapper publishMapper;

    @GetMapping("/")
    public String index(Model model) {
        List<UserWithPublish> publishList=publishService.select();
        model.addAttribute("publishlist",publishMapper.selectTwo());
        System.out.println(publishMapper.selectTwo().size()+"publish长度");
        System.out.println(publishList.size()+"帖子");
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    //发起帖子
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    //提交帖子
    @GetMapping("/do_publish")
    public String doPublish(Publish publish,HttpServletRequest request,Model model){
        User user =  (User)request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","未登录不能发布帖子");
            return "publish";
        }
        if (publish.getTitle()==null||publish.getTitle()==""){
            model.addAttribute("error","标题不能为空!");
            return "publish";
        }
        if (publish.getContent()==null||publish.getContent()==""){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if (publish.getTag()==null||publish.getTag()==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        publish.setGmtCreate(System.currentTimeMillis());
        publish.setGmtModified(publish.getGmtCreate());
        publish.setCreator(user.getUserId());
        publishService.insert(publish);
        return "redirect:/";
    }


}
