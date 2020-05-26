package com.xiaoling.community.controller;

import com.xiaoling.community.dto.CommentDto;
import com.xiaoling.community.mapper.CommentMapper;
import com.xiaoling.community.mapper.QuestionMapper;
import com.xiaoling.community.model.Comment;
import com.xiaoling.community.model.User;
import com.xiaoling.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;


    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(
            Model model,
            HttpServletRequest request,
            @RequestBody CommentDto commentDto

    ){
        User user = (User) request.getSession().getAttribute("user");
        Comment comment =new Comment();
        comment.setType(commentDto.getType());
        comment.setContent(commentDto.getContent());
        //comment.setParentId(questionMapper.getCreatorById(id));
        comment.setParentId(commentDto.getParentId());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommenter(1L);

        //CommentDto commentDto=commentService.create(comment,user);
        //model.addAttribute("comment",commentDto);
        commentMapper.create(comment);
        return commentDto;
    }

}
