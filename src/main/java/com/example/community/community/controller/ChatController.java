package com.example.community.community.controller;

import com.example.community.community.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @GetMapping("/user/{id}")
    public String userChat(@PathVariable(value = "id") int id, Model model){
        model.addAttribute("id",id);
        return "chat";
    }

    @MessageMapping("/message")
    public void sendMessage(Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getToUser(),"/message/get",message.getMsg());
        simpMessagingTemplate.convertAndSendToUser(message.getFromUser(),"/message/get",message.getMsg());
    }
}
