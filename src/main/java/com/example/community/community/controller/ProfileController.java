package com.example.community.community.controller;

import com.example.community.community.Service.NotificationService;
import com.example.community.community.Service.PublishService;
import com.example.community.community.Service.UserService;
import com.example.community.community.model.Notification;
import com.example.community.community.model.Publish;
import com.example.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private PublishService publishService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(value = "action") String action,
                          Model model,
                          HttpServletRequest request) {
        User user =  (User)request.getSession().getAttribute("user");
        if ("question".equals(action)) {
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            List<Publish> notificationList=notificationService.selectAll(user.getUserId());
            model.addAttribute("nitificationList",notificationList);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我的回复");
            request.getSession().removeAttribute("messageCount");
        }
        if (user==null){
            return "redirect:/";
        }
        List<Publish> publishListByCreator=publishService.selectByCreator(3);
        model.addAttribute("publishListByCreator",publishListByCreator);
        return "profile";
    }
}
