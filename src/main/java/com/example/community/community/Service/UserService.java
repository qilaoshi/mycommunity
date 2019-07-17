package com.example.community.community.Service;

import com.example.community.community.model.NotifiWithUserWithPublish;
import com.example.community.community.model.User;

import java.util.List;

public interface UserService {
    void insert(User user);
    User select(String token);
    void update(User user);
    User selectByAccountId(int accountId);
    User selectUser(String username);
    void insertToken(User user);
    List<NotifiWithUserWithPublish> selectNotifi(int id);
    void register(User user);
    User selectByUserId(int id);
}
