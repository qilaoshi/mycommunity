package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.PublishService;
import com.example.community.community.mapper.PublishMapper;
import com.example.community.community.model.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    private PublishMapper publishMapper;

    @Override
    public void insert(Publish publish) {
        publishMapper.insert(publish);
    }
}
