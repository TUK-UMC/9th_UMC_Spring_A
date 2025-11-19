package com.umc.prac.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseSuccessCode {

    // 일반 성공
    OK(HttpStatus.OK,
            "COMMON200",
            "요청에 성공하였습니다."),

    STORE_CREATE_SUCCESS(HttpStatus.CREATED,
            "STORE201",
            "가게 등록에 성공하였습니다."),

    REVIEW_CREATE_SUCCESS(HttpStatus.CREATED,
            "REVIEW201",
            "리뷰 등록에 성공하였습니다."),

    MISSION_CREATE_SUCCESS(HttpStatus.CREATED,
            "MISSION201",
            "미션 등록에 성공하였습니다."),

    MISSION_CHALLENGE_SUCCESS(HttpStatus.CREATED,
            "MISSION201_1",
            "미션 도전에 성공하였습니다."),

    // 조회 성공
    REVIEW_GET_SUCCESS(HttpStatus.OK,
            "REVIEW200",
            "리뷰 조회에 성공하였습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

