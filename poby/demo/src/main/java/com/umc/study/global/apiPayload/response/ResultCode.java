package com.umc.study.global.apiPayload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.umc.study.global.apiPayload.common.status.BaseStatus;

@Getter
@AllArgsConstructor
public enum ResultCode implements BaseStatus {

    OK(HttpStatus.OK, "COMMON_200", "성공적으로 처리되었습니다."),
    CREATED(HttpStatus.CREATED, "COMMON_201", "성공적으로 생성되었습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT, "COMMON_204", "성공적으로 삭제되었습니다."),

    // User Success
    USER_FETCH_OK(HttpStatus.OK, "USER_200", "유저 정보 조회 성공"),
    TOKEN_REISSUE_OK(HttpStatus.OK, "TOKEN_200", "토큰 재발급 성공"),
    USER_LOGOUT_OK(HttpStatus.OK, "USER_200", "유저 로그아웃 성공"),
    USER_LOGIN_OK(HttpStatus.OK, "USER_200", "유저 로그인 성공"),
    
    // Review Success
    REVIEW_CREATE_OK(HttpStatus.CREATED, "REVIEW_201", "리뷰가 성공적으로 생성되었습니다."),
    
    // Mission Success
    MISSION_CHALLENGE_OK(HttpStatus.CREATED, "MISSION_201", "미션 도전이 성공적으로 등록되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason(){
        return ReasonDto.builder()
                .status(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}
