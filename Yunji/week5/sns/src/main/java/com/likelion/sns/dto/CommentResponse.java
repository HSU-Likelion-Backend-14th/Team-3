package com.likelion.sns.dto;

import com.likelion.sns.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {
    private Integer commentId;
    private String content;
    private Integer postId;
    private Integer userId;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.postId = comment.getPost().getPostId();
        this.userId = comment.getUser().getUserId();
    }
}
