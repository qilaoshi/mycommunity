package com.example.community.community.mapper;

import com.example.community.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    void insert(Comment comment);
}
