package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.PublishService;
import com.example.community.community.mapper.PublishMapper;
import com.example.community.community.model.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    private PublishMapper publishMapper;

    @Override
    public void insert(Publish publish) {
        publishMapper.insert(publish);
    }

    @Override
    public List<Publish> select() {
        return publishMapper.select();
    }

    @Override
    public List<Publish> selectByCreator(int id) {
        return publishMapper.selectByCreator(id);
    }

    @Override
    public List<Publish> selectById(int id) {
        return publishMapper.selectById(id);
    }

    @Override
    public void updatViewCount(int id) {
        publishMapper.updateViewCount(id);
    }
}
