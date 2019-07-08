package com.example.community.community.mapper;

import com.example.community.community.model.Publish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublishMapper {
    void insert(Publish publish);
    List<Publish> select();
    List<Publish> selectByCreator(int id);
    List<Publish> selectById(int id);
    void updateViewCount(int id);
}
