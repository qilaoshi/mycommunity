package com.example.community.community.controller;

import com.example.community.community.Service.PublishService;
import com.example.community.community.model.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PublishController {
    @Autowired
    private PublishService publishService;

    @GetMapping("/publish_tag")
    public String publishTag(Publish publish){
        List<Publish> publishListByTag=publishService.selectByTag(publish);
        return null;
    }
}
