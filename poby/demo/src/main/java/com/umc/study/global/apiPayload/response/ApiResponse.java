package com.umc.study.global.apiPayload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {
    
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    public static <T> ApiResponse<T> onSuccess(T result) {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .code("200")
                .message("성공")
                .result(result)
                .build();
    }

    public static <T> ApiResponse<T> onFailure(String code, String message, T result) {
        return ApiResponse.<T>builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .result(result)
                .build();
    }
}