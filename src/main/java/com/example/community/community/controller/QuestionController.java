package com.example.community.community.controller;

import com.example.community.community.Service.CommentService;
import com.example.community.community.Service.FocusService;
import com.example.community.community.Service.PublishService;
import com.example.community.community.model.Comment;
import com.example.community.community.model.Focus;
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
    @Autowired
    private CommentService commentService;
    @Autowired
    private FocusService focusService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")int publishId,
                           Model model,HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        List<Publish> list=publishService.selectById(publishId);
        List<Comment> commentList=commentService.select(publishId);
        int commentCount=commentService.commentCount(publishId);
        publishService.updatViewCount(list.get(0).getId());
        List<Publish> tagList=publishService.selectByTag(list.get(0));
        Focus focuss=new Focus();
        focuss.setFocusers(user.getUserId());
        focuss.setFocused(list.get(0).getUser().getUserId());
        Focus focus=focusService.selectAll(focuss);
        model.addAttribute("onePublishList",list.get(0));
        model.addAttribute("commentList",commentList);
        model.addAttribute("commentCount",commentCount);
        model.addAttribute("tagList",tagList);
        model.addAttribute("focus",focus);
        return "question";
    }
}
