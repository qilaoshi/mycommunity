package com.example.community.community.controller;

import com.example.community.community.Service.PublishService;
import com.example.community.community.model.Publish;
import com.example.community.community.model.User;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private PublishService publishService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")int publishId,
                           Model model,
                           HttpServletRequest request){
        List<Publish> list=publishService.selectById(publishId);
        publishService.updatViewCount(list.get(0).getId());
        System.out.println(publishId);
        System.out.println(list+"list is");
        User user= (User) request.getSession().getAttribute("user");
        model.addAttribute("onePublishList",list.get(0));
        return "question";
    }
}
