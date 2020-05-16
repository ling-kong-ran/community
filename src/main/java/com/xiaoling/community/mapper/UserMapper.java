package com.xiaoling.community.mapper;

import com.xiaoling.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER(ACCOUNT_KEY,NAME,TAKEN,GMT_CREATE,GMT_MODIFIED) VALUES (#{accountid},#{name},#{token},#{gmtcreate},#{gmtmodified})")
    void insert(User user);
}
