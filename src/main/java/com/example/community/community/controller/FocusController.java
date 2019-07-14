package com.example.community.community.controller;

import com.example.community.community.Service.FocusService;
import com.example.community.community.dto.CommonJsonDto;
import com.example.community.community.model.Focus;
import com.example.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FocusController {
    @Autowired
    private FocusService focusService;


    @GetMapping("/focus")
    @ResponseBody
    public Object focus(int focused, HttpServletRequest request){
        System.out.println("关注者ID"+focused);
        CommonJsonDto commonJsonDto=new CommonJsonDto();
        User user=(User) request.getSession().getAttribute("user");
        if (user==null){
            commonJsonDto.setMsg("登陆后才能关注哦");
            return commonJsonDto;
        }else {
            Focus focus=new Focus();
            focus.setFocused(focused);
            focus.setFocusers(user.getUserId());
            focusService.insert(focus);
            commonJsonDto.setMsg("关注成功");
            return commonJsonDto;
        }
    }

    @GetMapping("/remove_focus")
    @ResponseBody
    public Object removeFocus(int focused,HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        Focus focus=new Focus();
        focus.setFocused(focused);
        focus.setFocusers(user.getUserId());
        focusService.delete(focus);
        CommonJsonDto commonJsonDto=new CommonJsonDto();
        commonJsonDto.setMsg("取关");
        return commonJsonDto;
    }
}
