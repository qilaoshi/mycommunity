package com.example.community.community.mapper;

import com.example.community.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insert(Comment comment);
    List<Comment> select(int id);
    int commentCount(int id);
    List<Comment> selectByCommentId(Comment comment);
}
