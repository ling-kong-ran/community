package com.xiaoling.community.mapper;

import com.xiaoling.community.model.Question;
import org.apache.ibatis.annotations.*;

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
    List<Question> listByUserId(@Param(value = "userId") Long userId, @Param(value = "offset") Integer offset,@Param(value = "size")Integer size);

    @Select("select count(1) from question where creator =#{userId}")
    Long countByUserId(@Param(value = "userId")Long userId);

    @Select("select * from question where id =#{id} ")
    Question getById(@Param(value = "id")Long id);

    @Update("update question set title=#{title},description=#{description},tag=#{tag},gmt_modified=#{gmtModified} where id =#{id}")
    void update(Question question);

    @Update("update question set view_count = view_count +1 where id =#{id}")
    void creaseViewCount(@Param(value = "id")Long id);

    @Select("select view_count from question where id =#{id}")
    Long getViewCountById(@Param(value = "id")Long id);

    @Select("select creator from question where id =#{id}")
    Long getCreatorById(@Param(value = "id")Long id);
}
