package com.likelion.sns.dto;

import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String content;
    private Integer userId;
}
