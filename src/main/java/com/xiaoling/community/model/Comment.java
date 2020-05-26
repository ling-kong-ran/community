package com.xiaoling.community.model;

import lombok.Data;

@Data
public class Comment {
    Long parentId;
    Long commenter;
    Integer type;
    String content;
    Long gmtCreate;
    Long gmtModified;

}
