package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.CommentService;
import com.example.community.community.mapper.CommentMapper;
import com.example.community.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }
}
