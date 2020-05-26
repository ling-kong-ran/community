package com.xiaoling.community.mapper;

import com.xiaoling.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment (parent_id,commenter,type,content,gmt_create,gmt_modified)values(#{parentId},#{commenter},#{type},#{content},#{gmtCreate},#{gmtModified})")
    void create(Comment comment) ;

}
