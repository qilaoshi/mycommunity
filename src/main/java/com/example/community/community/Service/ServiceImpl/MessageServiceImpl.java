package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.MessageService;
import com.example.community.community.mapper.MessageMapper;
import com.example.community.community.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void insertMessage(Message message) {
        messageMapper.insertMessage(message);

    }

    @Override
    public List<Message> selectByUser(Message message) {
        return messageMapper.selectByUser(message);
    }
}
