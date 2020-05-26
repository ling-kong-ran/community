package com.xiaoling.community.service;

import com.xiaoling.community.controller.QuestionController;
import com.xiaoling.community.dto.CommentDto;
import com.xiaoling.community.mapper.CommentMapper;
import com.xiaoling.community.mapper.QuestionMapper;
import com.xiaoling.community.model.Comment;
import com.xiaoling.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    public CommentDto create(Comment comment, User user) {
        CommentDto commentDto=new CommentDto();
        comment.setCommenter(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        commentMapper.create(comment);
        commentDto.setType(comment.getType());
        commentDto.setContent(comment.getContent());
       // commentDto.setCommenter(comment.getCommenter());



        return commentDto;
    }
}
