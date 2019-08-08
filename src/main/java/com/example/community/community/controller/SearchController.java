package com.example.community.community.controller;

import com.example.community.community.Service.PublishService;
import com.example.community.community.model.Publish;
import com.example.community.community.model.UserWithPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private PublishService publishService;
    @GetMapping("/search")
    public String search(String name, Model model){
        if (name==""||name==null){
            return "redirect:/";
        }
        List<UserWithPublish> searchList=publishService.selectByName(name);
        System.out.println(searchList.size());
        model.addAttribute("searchList",searchList);
        return "search";
    }

    @GetMapping("/category_search")
    public String category(String name,Model model){
        List<UserWithPublish> categoryList=publishService.category(name);
        List<UserWithPublish> publishList=publishService.select();
        List<UserWithPublish> hotList=new ArrayList<>();
        if (publishList.size()>=5){
            for (int i=0;i<5;i++){
                hotList.add(publishService.selectByHot().get(i));
            }
        }else {
            for (int i=0;i<publishList.size();i++){
                hotList.add(publishService.selectByHot().get(i));
            }
        }
        model.addAttribute("hotList",hotList);
        System.out.println(hotList.size());
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("categoryName",name);
        return "category";
    }

    @GetMapping("/select_by_time")
    public String selectByTime(){
        return "redirect:/";
    }

    @GetMapping("/select_by_hot")
    public String selectByHot(Model model){
        List<UserWithPublish> publishList=publishService.select();
        List<UserWithPublish> hotList=new ArrayList<>();
        if (publishList.size()>=5){
            for (int i=0;i<5;i++){
                hotList.add(publishService.selectByHot().get(i));
            }
        }else {
            for (int i=0;i<publishList.size();i++){
                hotList.add(publishService.selectByHot().get(i));
            }
        }
        model.addAttribute("hotList",hotList);
        List<UserWithPublish> hotArticleList=publishService.selectByHot();
        model.addAttribute("hotArticleList",hotArticleList);
        return "hot_article";
    }
}
