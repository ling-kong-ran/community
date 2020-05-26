package com.xiaoling.community.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long parentId;
    private Integer type;
    private String content;
}
