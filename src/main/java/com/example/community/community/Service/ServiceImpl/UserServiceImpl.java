package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.UserService;
import com.example.community.community.mapper.UserMapper;
import com.example.community.community.model.NotifiWithUserWithPublish;
import com.example.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User selectByAccountId(int accountId) {
        return userMapper.selectByAccountId(accountId);
    }

    @Override
    public User selectUser(String username) {
        return userMapper.selectUser(username);
    }

    @Override
    public void insertToken(User user) {
        userMapper.insertToken(user);
    }

    @Override
    public List<NotifiWithUserWithPublish> selectNotifi(int id) {
        return userMapper.selectNotifi(id);
    }

    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    @Override
    public User selectByUserId(int id) {
        return userMapper.selectByUserId(id);
    }

    @Override
    public void updateEmail(String email, int id) {
        userMapper.updateEmail(email, id);
    }

    @Override
    public void updatePassword(String password, int id) {
        userMapper.updatePassword(password,id);
    }

    @Override
    public void updateName(String name, int id) {
        userMapper.updateName(name, id);
    }

    @Override
    public void updateAvatarUrl(User user) {
        userMapper.updateAvatarUrl(user);
    }
}
