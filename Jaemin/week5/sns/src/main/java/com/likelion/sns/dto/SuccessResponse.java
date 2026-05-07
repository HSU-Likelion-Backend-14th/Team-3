package com.likelion.sns.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "공통 응답 포맷")
public class SuccessResponse<T> {

    @Schema(description = "성공 여부", example = "true")
    private boolean success;

    @Schema(description = "응답 메시지", example = "요청에 성공했습니다.")
    private String message;

    @Schema(description = "응답 데이터")
    private T data;

    public static <T> SuccessResponse<T> ok(T data) {
        return new SuccessResponse<>(true, "요청에 성공했습니다.", data);
    }

    public static <T> SuccessResponse<T> created(T data) {
        return new SuccessResponse<>(true, "성공적으로 작성되었습니다.", data);
    }
}