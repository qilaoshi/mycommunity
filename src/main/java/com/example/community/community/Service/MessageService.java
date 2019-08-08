package com.example.community.community.Service;

import com.example.community.community.model.Message;

import java.util.List;

public interface MessageService {
    void insertMessage(Message message);
    List<Message> selectByUser(Message message);
}
