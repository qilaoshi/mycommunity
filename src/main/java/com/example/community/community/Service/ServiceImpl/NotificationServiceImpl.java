package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.NotificationService;
import com.example.community.community.mapper.NotificationMapper;
import com.example.community.community.model.Notification;
import com.example.community.community.model.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Override
    public void insert(Notification notification) {
        notificationMapper.insert(notification);
    }

    @Override
    public int allCount(int receiver) {
        return notificationMapper.allCount(receiver);
    }

    @Override
    public void updateStatus(int nId) {
        notificationMapper.updateStatus(nId);
    }

}
