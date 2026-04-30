package com.likelion.sns.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {
    private boolean isSuccess;
    private String code;
    private String message;
    private T result;

    // 200 OK
    public static <T> ApiResponse<T> ok(T result, String message) {
        return new ApiResponse<>(true, "COMMON200", message, result);
    }

    // 201 CREATED
    public static <T> ApiResponse<T> created(T result, String message) {
        return new ApiResponse<>(true, "CREATED201", message, result);
    }
}