package com.example.community.community.Service;

import com.example.community.community.model.Publish;
import com.example.community.community.model.UserWithPublish;

import java.util.List;

public interface PublishService {
    void insert(Publish publish);
    List<UserWithPublish> select();
    List<Publish> selectByCreator(int id);
    List<Publish> selectById(int id);
    void updatViewCount(int id);
    List<Publish> selectByTag(Publish publish);
    List<UserWithPublish> selectByName(String name);
    List<UserWithPublish> selectByUserId(int id);
    List<UserWithPublish> category(String name);
    List<UserWithPublish> selectByHot();
    List<UserWithPublish> selectBytTime();
}
