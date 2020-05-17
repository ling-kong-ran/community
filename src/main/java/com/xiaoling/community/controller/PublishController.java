package com.xiaoling.community.controller;

import com.xiaoling.community.mapper.QuestionMapper;
import com.xiaoling.community.mapper.UserMapper;
import com.xiaoling.community.model.Question;
import com.xiaoling.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
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

        User user = null;

        Cookie[] cookies = request.getCookies();

        if (cookies ==null) {
            model.addAttribute("error","用户未登录");
            return "publish";}

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);//拿到session
                        //发布问题保存到数据库
                        Question question = new Question();

                        question.setTitle(title);
                        question.setDescription(description);
                        question.setTag(tag);
                        question.setGmt_Create(System.currentTimeMillis());
                        question.setGmt_Modified(question.getGmt_Create());
                        question.setCreator(user.getId());

                        questionMapper.create(question);
                        return "redirect:/";//发布完成跳回主页
                    }
                    break;
                }
            }


            return "redirect:/";







    }
}
