package com.xiaoling.community.controller;

import com.xiaoling.community.dto.QuestionDto;
import com.xiaoling.community.mapper.QuestionMapper;
import com.xiaoling.community.mapper.UserMapper;
import com.xiaoling.community.model.Question;
import com.xiaoling.community.model.User;
import com.xiaoling.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(
            @PathVariable("id") Integer id,
            Model model
    ){
        QuestionDto question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";

    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Integer id,
            @Autowired HttpServletRequest request,
            @Autowired Model model
    ){
        if (title==null || title=="")
        {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description==null || description=="")
        {
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if (description==null || description=="")
        {
            model.addAttribute("error","请给出标签");
            return "publish";
        }

        User user =(User) request.getSession().getAttribute("user");
        if (user != null) {
            request.getSession().setAttribute("user", user);//拿到session
            //发布问题保存到数据库
            Question question = new Question();

            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setId(id);

            questionService.createOrUpdate(question);
            return "redirect:/";//发布完成跳回主页
            // return "redirect:/";
        }
        model.addAttribute("error","用户未登录");
        return "publish";









    }}

