package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.PublishService;
import com.example.community.community.mapper.PublishMapper;
import com.example.community.community.model.Publish;
import com.example.community.community.model.UserWithPublish;
import com.sun.deploy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "publish_cache")
public class PublishServiceImpl implements PublishService {
    @Autowired
    private PublishMapper publishMapper;

    @Override
    public void insert(Publish publish) {
        publishMapper.insert(publish);
    }

    @Override
    public List<UserWithPublish> select() {
        return publishMapper.selectTwo();
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

    @Override
    public List<Publish> selectByTag(Publish publish) {
        String[] tags=StringUtils.splitString(publish.getTag(),",");
        String regexpTag= Arrays.stream(tags).collect(Collectors.joining("|"));
        System.out.println("tag si"+regexpTag+publish.getId());
        Publish publish1=new Publish();
        publish1.setId(publish.getId());
        publish1.setTag(regexpTag);
        return publishMapper.selectByTag(publish1);
    }

    @Override
    public List<UserWithPublish> selectByName(String name) {
        return publishMapper.selectByName(name);
    }

    @Override
    public List<UserWithPublish> selectByUserId(int id) {
        return publishMapper.selectByUserId(id);
    }

    @Override
    public List<UserWithPublish> category(String name) {
        return publishMapper.category(name);
    }

    @Override
    public List<UserWithPublish> selectByHot() {
        return publishMapper.selectByHot();
    }

    @Override
    public List<UserWithPublish> selectBytTime() {
        return publishMapper.selectBytTime();
    }
}
