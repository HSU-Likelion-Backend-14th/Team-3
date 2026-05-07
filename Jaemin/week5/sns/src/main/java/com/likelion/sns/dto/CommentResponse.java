package com.likelion.sns.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "댓글 응답")
public class CommentResponse {

    @Schema(description = "댓글 ID", example = "1")
    private Integer commentId;

    @Schema(description = "댓글 내용", example = "안녕하세요")
    private String content;

    @Schema(description = "작성자 ID", example = "1")
    private Integer userId;

    @Schema(description = "작성자 닉네임", example = "유재민")
    private String nickname;

    @Schema(description = "작성일시", example = "2026-04-30T12:00:00")
    private LocalDateTime createdAt;
}