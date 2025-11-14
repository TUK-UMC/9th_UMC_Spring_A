package com.umc.study.global.apiPayload.error.exception;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import com.umc.study.global.apiPayload.error.dto.ErrorCode;
import com.umc.study.global.apiPayload.error.dto.ErrorResponse.FieldError;

/**
 * 비즈니스 예외를 표현하는 추상적 예외 클래스.
 * ErrorCode와 필드 단위 에러 정보를 담고 있다.
 */
@Getter
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;
    private final List<FieldError> fieldErrors;

    /**
     * ErrorCode만 받는 생성자.
     * 이 경우 message는 ErrorCode의 메시지를 사용한다.
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.fieldErrors = Collections.emptyList();
    }

    /**
     * 개발자가 직접 message를 지정할 수 있는 생성자.
     * ErrorCode의 메시지 대신 별도 메시지를 사용한다.
     */
    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.fieldErrors = Collections.emptyList();
    }

    /**
     * 필드 에러 리스트를 함께 넘길 수 있는 생성자.
     */
    public BusinessException(ErrorCode errorCode, List<FieldError> fieldErrors) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        // 넘어온 fieldErrors가 null이면 빈 리스트로 처리하고, 있으면 변경 불가능한 리스트로 wrapping
        this.fieldErrors = fieldErrors == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(new ArrayList<>(fieldErrors));
    }

    /**
     * 예외 원인(cause)까지 함께 전달하고 싶은 경우.
     * 디버깅 필요 시 원인 예외 추적 가능.
     */
    public BusinessException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.fieldErrors = Collections.emptyList();
    }
}
