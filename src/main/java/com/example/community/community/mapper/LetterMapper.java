package com.example.community.community.mapper;

import com.example.community.community.model.LetterWithUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LetterMapper {
    void insert(LetterWithUser letterWithUser);
    List<LetterWithUser> selectByUserId(int id);
    List<LetterWithUser> select(LetterWithUser letterWithUser);
    int notReadCount(int id);
    void updateType(LetterWithUser letterWithUser);
}
