package com.xiaoling.community.mapper;

import com.xiaoling.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER(ACCOUNT_KEY,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED,AVATAR_URL,BIO) VALUES (#{accountKey},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl},#{bio})")
    void insert(User user);
    @Select("select * from user where TOKEN= #{token} ")//从数据库中查找token以便持久化登录
    User findByToken(@Param("token") String token);
    @Select("select * from user where id= #{id} ")
    User findById(@Param("id")Integer id);


    @Select("select * from user where account_key= #{AccountKey} ")
    User findByAccountKey(@Param("AccountKey")String AccountKey);//今天就在这里的AccountKey上载坑了


    @Update({"update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}"})
    void update(User user);
}
