package com.xiaoling.community.mapper;

import com.xiaoling.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag)values(#{title},#{description},#{gmt_Create},#{gmt_Modified},#{creator},#{tag})")
    void create(Question question);
}
