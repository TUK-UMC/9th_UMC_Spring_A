package com.umc.study.global.apiPayload.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.umc.study.global.apiPayload.common.status.BaseStatus;
import com.umc.study.global.apiPayload.response.ReasonDto;

@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseStatus {
    // Global
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "서버 에러입니다. 관리자에게 문의하세요."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON_401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_403", "금지된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_404", "찾을 수 없는 요청입니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_404", "찾을 수 없는 요청입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_405", "허용되지 않은 메소드입니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_400", "잘못된 요청입니다."),
    INPUT_VALUE_INVALID(HttpStatus.BAD_REQUEST, "REQUEST_400", "요청사항에 필수 인자가 누락되었습니다"),
    HTTP_MESSAGE_NOT_READABLE(HttpStatus.BAD_REQUEST, "G005", "request message body가 없거나, 값 타입이 올바르지 않습니다."),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "COMMON_415", "지원되지 않는 MediaType입니다."),
    
    // Store & Review Errors
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_404", "가게를 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_404", "멤버를 찾을 수 없습니다."),
    
    // Mission Errors
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_404", "미션을 찾을 수 없습니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_MISSION_404", "진행중인 미션을 찾을 수 없습니다."),
    
    // Page Errors
    INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "PAGE_400", "페이지 번호는 1 이상이어야 합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .status(httpStatus)
                .code(this.code)
                .message(this.message)
                .build();
    }

}
