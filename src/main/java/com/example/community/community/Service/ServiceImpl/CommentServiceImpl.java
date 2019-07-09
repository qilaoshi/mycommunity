package com.example.community.community.Service.ServiceImpl;

import com.example.community.community.Service.CommentService;
import com.example.community.community.mapper.CommentMapper;
import com.example.community.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public List<Comment> select(int id) {
        return commentMapper.select(id);
    }

    @Override
    public int commentCount(int id) {
        return commentMapper.commentCount(id);
    }

    @Override
    public List<Comment> selectByCommentId(Comment comment) {
        return commentMapper.selectByCommentId(comment);
    }
}
