package com.likelion.sns.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "댓글 생성 요청")
public class CommentCreateRequest {

    @Schema(description = "댓글 내용", example = "이 게시글 정말 좋아요!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @Schema(description = "댓글 작성자 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;
}
