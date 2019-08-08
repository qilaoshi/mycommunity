package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.LetterService;
import com.example.community.community.mapper.LetterMapper;
import com.example.community.community.model.LetterWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterServiceImpl implements LetterService {
    @Autowired
    private LetterMapper letterMapper;
    @Override
    public void insert(LetterWithUser letterWithUser) {
        letterMapper.insert(letterWithUser);
    }

    @Override
    public List<LetterWithUser> select(LetterWithUser letterWithUser) {
        return letterMapper.select(letterWithUser);
    }

    @Override
    public List<LetterWithUser> selectByUserId(int id) {
        return letterMapper.selectByUserId(id);
    }

    @Override
    public int notReadCount(int id) {
        return letterMapper.notReadCount(id);
    }

    @Override
    public void updateType(LetterWithUser letterWithUser) {
        letterMapper.updateType(letterWithUser);
    }
}
