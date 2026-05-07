package com.likelion.sns.dto;

import com.likelion.sns.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "댓글 응답 데이터")
public class CommentResponseDto {

    @Schema(description = "댓글 ID", example = "1")
    private Integer commentId;

    @Schema(description = "댓글 내용", example = "첫 번째 댓글")
    private String content;

    @Schema(description = "작성 시간", example = "2026-11-11T11:11:11")
    private LocalDateTime createdAt;

    @Schema(description = "유저 ID", example = "1")
    private Integer authorId;

    @Schema(description = "게시글 ID", example = "1")
    private Integer postId;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.authorId = comment.getUser().getUserId();
        this.postId = comment.getPost().getPostId();
    }
}
