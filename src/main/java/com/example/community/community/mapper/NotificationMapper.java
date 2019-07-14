package com.example.community.community.mapper;

import com.example.community.community.model.Notification;
import com.example.community.community.model.Publish;

import java.util.List;

public interface NotificationMapper {
    void insert(Notification notification);
    int allCount(int receiver);
    void updateStatus(int nId);
}
