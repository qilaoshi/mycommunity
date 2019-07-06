package com.example.community.community.mapper;

import com.example.community.community.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
     void insert(User user);
}
