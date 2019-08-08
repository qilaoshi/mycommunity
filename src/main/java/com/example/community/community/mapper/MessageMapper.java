package com.example.community.community.mapper;

import com.example.community.community.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    void insertMessage(Message message);
    List<Message> selectByUser(Message message);
}
