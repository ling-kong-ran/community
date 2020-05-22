package com.xiaoling.community.service;

import com.xiaoling.community.mapper.UserMapper;
import com.xiaoling.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUptaed(User user) {
        User dbUser=userMapper.findByAccountId(user.getAccountKey());
        if(dbUser==null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //更新

            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setBio(user.getBio());
            userMapper.update(dbUser);


        }
    }
}
