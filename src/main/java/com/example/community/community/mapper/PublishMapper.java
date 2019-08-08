package com.example.community.community.mapper;

import com.example.community.community.model.Publish;
import com.example.community.community.model.UserWithPublish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublishMapper {

    void insert(Publish publish);

    List<UserWithPublish> select();

    List<Publish> selectByCreator(int id);

    List<Publish> selectById(int id);

    void updateViewCount(int id);

    List<Publish> selectByTag(Publish publish);

    List<UserWithPublish> selectTwo();

    List<UserWithPublish> selectByName(String name);

    List<UserWithPublish> selectByUserId(int id);

    List<UserWithPublish> category(String name);

    List<UserWithPublish> selectByHot();

    List<UserWithPublish> selectBytTime();

}
