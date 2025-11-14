package com.umc.study.global.apiPayload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.umc.study.global.apiPayload.common.status.BaseStatus;
import com.umc.study.global.apiPayload.error.dto.ErrorCode;

/**
 * 성공/실패를 막론하고
 * 공통 응답에 활용할 수 있는 Response 클래스 예시
 *
 * BaseStatus: 상태코드와 메시지를 담고 있는 인터페이스 (ex. ErrorCode, ResultCode 등)
 */
@Getter
@Builder
@JsonPropertyOrder({"httpStatus", "code", "message", "result"})
public class CommonResponse<T> {

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @JsonInclude(Include.NON_NULL)
    private final T result;

    /**
     * 풀 생성자
     * 필요하다면 @AllArgsConstructor로 대체할 수도 있습니다.
     */
    public CommonResponse(HttpStatus httpStatus, String code, String message, T result) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    /**
     * BaseStatus를 활용한 성공/실패 응답 (result 포함)
     */
    public static <T> CommonResponse<T> of(BaseStatus status, T result) {
        return new CommonResponse<>(
                status.getReason().getStatus(),
                status.getReason().getCode(),
                status.getReason().getMessage(),
                result
        );
    }

    /**
     * BaseStatus를 활용한 성공/실패 응답 (result 없음)
     */
    public static <T> CommonResponse<T> of(BaseStatus status) {
        return new CommonResponse<>(
                status.getReason().getStatus(),
                status.getReason().getCode(),
                status.getReason().getMessage(),
                null
        );
    }

    /**
     * 상태가 2xx(성공)인 경우 가독성을 위해 별도 메서드 예시
     */
    public static <T> CommonResponse<T> ok(BaseStatus status) {
        return of(status);
    }

    /**
     * ErrorCode를 직접 받아 처리하는 경우가 필요하다면,
     * 아래와 같이 overloading할 수 있습니다.
     */
    public static <T> CommonResponse<T> of(ErrorCode errorCode) {
        return new CommonResponse<>(
                errorCode.getHttpStatus(),
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
    }
}
