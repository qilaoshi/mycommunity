package com.example.community.community.mapper;

import com.example.community.community.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
     void insert(User user);
     User select(String token);
     void update(User user);
     User selectByAccountId(int accountId);
     User selectUser(String username);
     void insertToken(User user);
}
