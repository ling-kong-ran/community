package com.xiaoling.community.service;

import com.xiaoling.community.dto.CommentDto;
import com.xiaoling.community.exception.MyExceptionCode;
import com.xiaoling.community.exception.MyExceptionCodeEnum;
import com.xiaoling.community.mapper.CommentMapper;
import com.xiaoling.community.mapper.QuestionMapper;
import com.xiaoling.community.model.Comment;
import com.xiaoling.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void create(Comment comment) {
        if (comment.getParentId() == null ||comment.getParentId()==0){
            throw new MyExceptionCode(MyExceptionCodeEnum.COMMENT_TYPE_NOT_CHOICE);
        }else {commentMapper.create(comment);}

    }
}
