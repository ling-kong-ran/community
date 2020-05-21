package com.xiaoling.community.mapper;

import com.xiaoling.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER(ACCOUNT_KEY,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED,AVATAR_URL,BIO) VALUES (#{accountKey},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl},#{bio})")
    void insert(User user);
    @Select("select * from user where TOKEN= #{token} ")//从数据库中查找token以便持久化登录
    User findByToken(@Param("token") String token);
    @Select("select * from user where id= #{id} ")
    User findById(@Param("id")Integer id);
}
