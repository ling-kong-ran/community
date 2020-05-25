package com.xiaoling.community.service;

import com.xiaoling.community.dto.PaginationDto;
import com.xiaoling.community.dto.QuestionDto;
import com.xiaoling.community.exception.MyExceptionCode;
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

    public PaginationDto list(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount = questionMapper.count();
        paginationDto.setPagination(totalCount,page,size);
        if(page<1){
            page=1;
        }
        if(page>paginationDto.getTotalPage()) {
            page=paginationDto.getTotalPage();
        }
        //size*(page-1)
        Integer offset = size * (page -1);
        List<Question> questions= questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList =new ArrayList<>();

        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            //把question对象的所有属性拷贝到questiondto
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;
    }

    public PaginationDto list(Integer userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);

        if(totalCount % size==0){
            totalPage =totalCount/size;
        }else {
            totalPage =totalCount/size+1;
        }
        paginationDto.setPagination(totalPage,page,size);
        if(page<1){
            page=1;
        }
        if(page>paginationDto.getTotalPage()) {
            page=paginationDto.getTotalPage();
        }
        //size*(page-1)
        Integer offset = size * (page -1);
        List<Question> questions= questionMapper.listByUserId(userId,offset,size);
        List<QuestionDto> questionDtoList =new ArrayList<>();

        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            //把question对象的所有属性拷贝到questiondto
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;
    }

    public QuestionDto getById(Integer id) {

        Question question=questionMapper.getById(id);
        if(question==null) throw new MyExceptionCode((long) 404,"您找的问题被偷走了，要不然换个姿势试试");
        User user=userMapper.findById(question.getCreator());
        QuestionDto questionDto = new QuestionDto();
        //把question对象的所有属性拷贝到questiondto
        BeanUtils.copyProperties(question,questionDto);
        questionDto.setUser(user);
        return  questionDto;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建

            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            //update
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }

    public Integer creaseViewCount(Integer id) {
        questionMapper.creaseViewCount(id);

        return questionMapper.getViewCountById(id);
    }
}
