package com.example.community.community.mapper;

import com.example.community.community.model.Publish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PublishMapper {
    void insert(Publish publish);
}
