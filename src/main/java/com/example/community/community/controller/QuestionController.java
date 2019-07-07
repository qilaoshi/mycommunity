package com.example.community.community.controller;

import com.example.community.community.Service.PublishService;
import com.example.community.community.model.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private PublishService publishService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")int publishId,
                           Model model){
        List<Publish> list=publishService.selectById(publishId);
        model.addAttribute("onePublishList",list);
        return "question";
    }
}
