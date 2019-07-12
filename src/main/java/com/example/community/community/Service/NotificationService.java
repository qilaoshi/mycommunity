package com.example.community.community.Service;

import com.example.community.community.model.Notification;
import com.example.community.community.model.Publish;

import java.util.List;

public interface NotificationService {
    void insert(Notification notification);
    int allCount(int receiver);
    List<Publish> selectAll(int id);
}
