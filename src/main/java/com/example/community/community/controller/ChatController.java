package com.example.community.community.controller;

import com.example.community.community.Service.LetterService;
import com.example.community.community.Service.MessageService;
import com.example.community.community.Service.UserService;
import com.example.community.community.model.LetterWithUser;
import com.example.community.community.model.Message;
import com.example.community.community.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private LetterService letterService;
    @GetMapping("/user/{id}")
    public String userChat(@PathVariable(value = "id") int id, Model model, HttpServletRequest request){
        User toUser=userService.selectByUserId(id);
        User fromUser=(User)request.getSession().getAttribute("user");
        Message message=new Message();
        message.setFromUser(fromUser.getUserId());
        message.setToUser(toUser.getUserId());
        List<Message> messageList=messageService.selectByUser(message);
        if (messageList.size()!=0&&messageList!=null){
            model.addAttribute("messageList",messageList);
        }
        if (fromUser!=null) {
            model.addAttribute("fromUser", fromUser);
            model.addAttribute("toUser", toUser);
            System.out.println("执行查看操作");
            LetterWithUser letterWithUser=new LetterWithUser();
            System.out.println("执行查看操作");
            letterWithUser.setFromUserId(id);
            System.out.println("执行查看操作");
            letterWithUser.setToUserId(fromUser.getUserId());
            System.out.println(letterWithUser.getToUserId()+"分别是"+letterWithUser.getFromUserId());
            letterService.updateType(letterWithUser);
            return "chat";
        }
        return "redirect:/";
    }

    @MessageMapping("/message")
    public void sendMessage(Message message){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        Date d= new Date();
        String str = sdf.format(d);
        message.setTime(str);
        ObjectMapper mapper = new ObjectMapper();
        String json=null;
        try {
            json = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getToUser()),"/message/get",json);
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getFromUser()),"/message/get",json);
        messageService.insertMessage(message);
    }
}
