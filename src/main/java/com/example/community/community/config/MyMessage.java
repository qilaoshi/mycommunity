package com.example.community.community.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class MyMessage {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
//    @Scheduled(fixedRate = 1000)
////    public void sendMessage(){
////        System.out.println("来自服务的信息");
////        simpMessagingTemplate.convertAndSend("/server/sendMessageByServer","来了");
////    }
//    @Scheduled(fixedRate = 1000)
//    public void sendMessage(){
//        System.out.println("发信息");
//        simpMessagingTemplate.convertAndSendToUser("1","/sendMessageByServer","message");
//    }
}
