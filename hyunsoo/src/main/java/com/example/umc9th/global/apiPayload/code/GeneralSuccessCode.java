package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200",
            "성공"),
    CREATED(HttpStatus.CREATED,
            "COMMON201",
            "생성 성공"),
    ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202",
            "요청 접수 성공"),
    NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204",
            "성공 (응답 데이터 없음)");

    private final HttpStatus status;
    private final String code;
    private final String message;
}