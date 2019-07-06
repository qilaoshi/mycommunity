package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.UserService;
import com.example.community.community.mapper.UserMapper;
import com.example.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public User select(String token) {
        return userMapper.select(token);
    }
}
