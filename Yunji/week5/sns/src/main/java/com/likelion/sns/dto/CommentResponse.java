package com.likelion.sns.dto;

import com.likelion.sns.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.awt.*;

@Getter
@Schema(description = "댓글 응답")
public class CommentResponse {

    @Schema(description = "댓글 ID", example = "1")
    private Integer commentId;

    @Schema(description = "댓글 내용", example = "이 게시글 정말 좋아요!")
    private String content;

    @Schema(description = "댓글이 달린 게시글 ID", example = "1")
    private Integer postId;

    @Schema(description = "댓글 작성자 ID", example = "1")
    private Integer userId;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.postId = comment.getPost().getPostId();
        this.userId = comment.getUser().getUserId();
    }
}
