package com.example.community.community.Service;

import com.example.community.community.model.User;

public interface UserService {
    void insert(User user);
    User select(String token);
    void update(User user);
    User selectByAccountId(int accountId);
}
