package com.xiaoling.community.controller;


import com.xiaoling.community.dto.QuestionDto;
import com.xiaoling.community.model.User;
import com.xiaoling.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(
            @PathVariable("id") Integer id,
            Model model,
            HttpServletRequest request
    ){


            QuestionDto questionDto =questionService.getById(id);
            //if(questionDto.getId() == null) return "index";
            model.addAttribute("question",questionDto);
            return "/question";

    }
    @GetMapping("/question/error")
    public String error(
            Model model
    ){
        model.addAttribute("questionerror","请登录");
        return "redirect:/";
    }



}
