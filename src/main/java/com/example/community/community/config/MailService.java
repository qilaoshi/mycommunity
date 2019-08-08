package com.example.community.community.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendSimpleMail(String from,String to,String cc,String subject,String content){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }
}
