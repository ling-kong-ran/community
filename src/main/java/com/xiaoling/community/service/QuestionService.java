package com.xiaoling.community.service;

import com.xiaoling.community.controller.IndexController;
import com.xiaoling.community.dto.PageDto;
import com.xiaoling.community.dto.QuestionDto;
import com.xiaoling.community.mapper.QuestionMapper;
import com.xiaoling.community.mapper.UserMapper;
import com.xiaoling.community.model.Question;
import com.xiaoling.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDto> list() {
        List<Question> questions= questionMapper.list();
        List<QuestionDto> questionDtoList =new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            //把question对象的所有属性拷贝到questiondto
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }
}
