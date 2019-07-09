package com.example.community.community.Service;

import com.example.community.community.model.Comment;

import java.util.List;

public interface CommentService {
    void insert(Comment comment);
    List<Comment> select(int id);
    int commentCount(int id);
    List<Comment> selectByCommentId(Comment comment);
}
