package com.xiaoling.community.mapper;

import com.xiaoling.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag)values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{size} offset #{offset}")
    List<Question> list( @Param(value = "offset") Integer offset,@Param(value = "size")Integer size) ;

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator =#{userId} limit #{size} offset #{offset}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset,@Param(value = "size")Integer size);

    @Select("select count(1) from question where creator =#{userId}")
    Integer countByUserId(@Param(value = "userId")Integer userId);
}
