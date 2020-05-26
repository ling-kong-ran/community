package com.xiaoling.community.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String accountKey;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
    private String bio;
}
