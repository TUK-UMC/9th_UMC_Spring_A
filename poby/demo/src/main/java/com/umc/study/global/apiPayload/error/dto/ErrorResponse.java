package com.umc.study.global.apiPayload.error.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@JsonPropertyOrder({"timestamp", "traceId", "httpStatus", "code", "message", "errors", "path"})
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String traceId;                  // 에러/로그 추적용 ID
    private HttpStatus httpStatus;
    private String code;
    private String message;                  // 전역 에러 메시지
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FieldError> errors;         // 필드 단위 에러 정보
    private String path;                     // 요청 경로

    /**
     * 전역 메시지 + 필드 에러 + path + traceId를 모두 처리할 수 있는 생성자
     */
    private ErrorResponse(HttpStatus httpStatus,
                          String code,
                          String message,
                          List<FieldError> errors,
                          String path,
                          String traceId) {
        this.timestamp = LocalDateTime.now();
        this.traceId = traceId;
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        // 필드 에러가 없거나 비어 있으면 null
        this.errors = (errors == null || errors.isEmpty()) ? null : errors;
        this.path = path;
    }

    /**
     * ErrorCode를 사용하는 생성자
     */
    private ErrorResponse(ErrorCode errorCode,
                          String globalMessage,
                          List<FieldError> errors,
                          String path,
                          String traceId) {
        this(
                errorCode.getHttpStatus(),
                errorCode.getCode(),
                // 전역 메시지가 있으면 사용, 그렇지 않으면 ErrorCode 내 메시지 사용
                globalMessage != null ? globalMessage : errorCode.getMessage(),
                errors,
                path,
                traceId
        );
    }

    /* ─────────────────────────────────────────────────────────
       정적 팩터리 메서드들
       필요에 따라 오버로드하여 다양한 상황을 처리
       (ex. 필드 에러만 있는 경우, 메시지만 있는 경우 등)
       ───────────────────────────────────────────────────────── */

    /**
     * 일반적인 에러 응답 생성 - 필드 에러 리스트와 함께
     */
    public static ErrorResponse of(ErrorCode errorCode,
                                   List<FieldError> fieldErrors,
                                   String path,
                                   String traceId) {
        return new ErrorResponse(errorCode, null, fieldErrors, path, traceId);
    }

    /**
     * 필드 에러 없이 전역 메시지만 있는 에러 응답
     */
    public static ErrorResponse of(ErrorCode errorCode,
                                   String path,
                                   String traceId) {
        return new ErrorResponse(errorCode, null, null, path, traceId);
    }

    /**
     * ConstraintViolationException 기반 오류 응답 생성
     */
    public static ErrorResponse of(ErrorCode errorCode,
                                   Set<ConstraintViolation<?>> violations,
                                   String path,
                                   String traceId) {
        List<FieldError> fieldErrors = FieldError.of(violations);
        return new ErrorResponse(errorCode, null, fieldErrors, path, traceId);
    }

    /**
     * MethodArgumentNotValidException 기반 오류 응답 생성
     */
    public static ErrorResponse of(ErrorCode errorCode,
                                   BindingResult bindingResult,
                                   String path,
                                   String traceId) {
        List<FieldError> fieldErrors = FieldError.of(bindingResult);
        return new ErrorResponse(errorCode, null, fieldErrors, path, traceId);
    }

    /**
     * 단일 필드 오류 응답 생성
     * - 필드 에러와 전역 메시지를 함께 사용할 수도 있도록 오버로드 예시
     */
    public static ErrorResponse of(ErrorCode errorCode,
                                   String globalMessage,
                                   String field,
                                   String path,
                                   String traceId) {
        if (field == null) {
            // 단순 전역 메시지만 설정
            return new ErrorResponse(errorCode, globalMessage, null, path, traceId);
        } else {
            // 필드 에러 리스트 생성
            List<FieldError> fieldErrors = FieldError.of(field, "", globalMessage);
            // globalMessage 자리에 null을 주면 ErrorCode 기본 메시지가 아닌, 이곳에서 만든 필드 메시지 사용
            return new ErrorResponse(errorCode, null, fieldErrors, path, traceId);
        }
    }

    @Getter
    public static class FieldError {
        private String field;
        private String rejectedValue;
        private String reason;

        public FieldError(String field, String rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        /**
         * BindingResult에서 필드 오류 추출
         */
        public static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }

        /**
         * ConstraintViolation에서 필드 오류 추출
         */
        public static List<FieldError> of(Set<ConstraintViolation<?>> violations) {
            return violations.stream()
                    .map(violation -> new FieldError(
                            violation.getPropertyPath().toString(),
                            violation.getInvalidValue() == null ? "" : violation.getInvalidValue().toString(),
                            violation.getMessage()))
                    .collect(Collectors.toList());
        }

        /**
         * 단일 필드 오류 리스트 생성
         */
        public static List<FieldError> of(String field, String rejectedValue, String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, rejectedValue, reason));
            return fieldErrors;
        }
    }
}
