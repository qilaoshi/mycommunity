package com.example.community.community.controller;

import com.example.community.community.Service.PublishService;
import com.example.community.community.Service.UserService;
import com.example.community.community.model.Publish;
import com.example.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloController {
    @Autowired
    private UserService userService;
    @Autowired
    private PublishService publishService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userService.select(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        }
        return "index";
    }

    //发起帖子
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    //提交帖子
    @GetMapping("/do_publish")
    public String doPublish(Publish publish,HttpServletRequest request,Model model){
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userService.select(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
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
        publish.setCreator(user.getId());
        publishService.insert(publish);
        return "redirect:/";
    }


}
