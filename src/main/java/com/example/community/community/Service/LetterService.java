package com.example.community.community.Service;

import com.example.community.community.model.LetterWithUser;

import java.util.List;

public interface LetterService {
    void insert(LetterWithUser letterWithUser);
    List<LetterWithUser> select(LetterWithUser letterWithUser);
    List<LetterWithUser> selectByUserId(int id);
    int notReadCount(int id);
    void updateType(LetterWithUser letterWithUser);
}
