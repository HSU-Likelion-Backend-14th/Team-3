package com.likelion.sns.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "댓글 작성 요청 데이터")
public class CommentRequestDto {

    @NotBlank(message = "내용을 입력해주세요.")
    @Schema(description = "댓글 내용", example = "댓글 작성~~")
    private String content;

    @NotNull(message = "유저 ID는 필수입니다.")
    @Schema(description = "유저 ID", example = "1")
    private Integer authorId;
}
