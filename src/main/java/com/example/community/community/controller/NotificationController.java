package com.example.community.community.controller;

import com.example.community.community.Service.LetterService;
import com.example.community.community.Service.NotificationService;
import com.example.community.community.dto.CommonJsonDto;
import com.example.community.community.model.LetterWithUser;
import com.example.community.community.model.Notification;
import com.example.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private LetterService letterService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/notification")
    @ResponseBody
    public Object nitification(@RequestBody Notification notification,
                               HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        CommonJsonDto commonJsonDto=new CommonJsonDto();
        if (user==null){
            commonJsonDto.setMsg("未登录");
            return commonJsonDto;
        }else if(user.getUserId()==notification.getReceiver()){
            commonJsonDto.setMsg("ok");
            return commonJsonDto;
        }
        else {
            Long createTime = System.currentTimeMillis();
            notification.setGmtCreate(createTime);
            notification.setNotifier(user.getUserId());
            notificationService.insert(notification);
            commonJsonDto.setMsg("ok");
            return commonJsonDto;
        }
    }



    @MessageMapping("/letter")
    public void Letter(LetterWithUser letterWithUser){
        letterService.insert(letterWithUser);
        List<LetterWithUser> letterWithUserList=letterService.select(letterWithUser);
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(letterWithUser.getToUserId()),"/server/sendMessageByServer", letterWithUserList.size());
        System.out.println("有人发消息了");
        System.out.println(letterWithUserList.size());
    }
}
