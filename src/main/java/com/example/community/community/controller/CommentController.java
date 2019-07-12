package com.example.community.community.controller;

import com.example.community.community.Service.CommentService;
import com.example.community.community.dto.CommonJsonDto;
import com.example.community.community.model.Comment;
import com.example.community.community.model.User;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object postComment(@RequestBody Comment comment,
                              HttpServletRequest request){
        if (request.getSession().getAttribute("user")==null){
            CommonJsonDto commonJsonDto=new CommonJsonDto();
            commonJsonDto.setMsg("请登陆后评论");
            commonJsonDto.setCode(0);
            return commonJsonDto;
        }else {
            User user=(User)request.getSession().getAttribute("user");
            comment.setCommentator(user.getUserId());
            comment.setGmtCreate(System.currentTimeMillis());
            comment.setGmtModified(comment.getGmtCreate());
            commentService.insert(comment);
            CommonJsonDto commonJsonDto = new CommonJsonDto();
            commonJsonDto.setMsg("成功");
            commonJsonDto.setCode(0);
            return commonJsonDto;
        }
    }

    @PostMapping("/comments")
    @ResponseBody
    public Object selectComments(@RequestBody Comment comment,
                                 HttpServletRequest request){
        System.out.println("c is "+comment.getId());
        System.out.println("type is "+ comment.getType());
        List<Comment> list=commentService.selectByCommentId(comment);
        System.out.println(commentService.selectByCommentId(comment).size()+"size");
        CommonJsonDto<List<Comment>> commonJsonDto=new CommonJsonDto();
        if (commentService.selectByCommentId(comment)==null&&commentService.selectByCommentId(comment).size()==0){
            commonJsonDto.setMsg("false");
        }else {
            commonJsonDto.setData(list);
            commonJsonDto.setMsg("ok");
        }
        return commonJsonDto;
    }

}
