package com.example.community.community.controller;

import com.example.community.community.Service.NotificationService;
import com.example.community.community.dto.CommonJsonDto;
import com.example.community.community.model.Notification;
import com.example.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notification")
    @ResponseBody
    public Object nitification(@RequestBody Notification notification,
                               HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        CommonJsonDto commonJsonDto=new CommonJsonDto();
        if (user==null){
            commonJsonDto.setMsg("未登录");
            return commonJsonDto;
        }else {
            Long createTime = System.currentTimeMillis();
            notification.setGmtCreate(createTime);
            notification.setNotifier(user.getUserId());
            notificationService.insert(notification);
            commonJsonDto.setMsg("ok");
            return commonJsonDto;
        }
    }
}
