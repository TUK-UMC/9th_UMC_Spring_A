package com.umc.study.global.apiPayload.error.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import com.umc.study.global.apiPayload.error.dto.ErrorCode;
import com.umc.study.global.apiPayload.error.dto.ErrorResponse;
import com.umc.study.global.apiPayload.error.exception.BusinessException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 예외 응답을 생성하면서 동시에 로그를 찍는 편의 메서드 - traceId(또는 correlationId)가 필요하다면 이 메서드 안에서 세팅
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(ErrorCode errorCode,
                                                             String message,
                                                             String field,
                                                             HttpServletRequest request,
                                                             Throwable ex) {
        String traceId = MDC.get("traceId");

        if (errorCode.getHttpStatus().is5xxServerError()) {
            log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        } else if (errorCode.getHttpStatus().is4xxClientError()) {
            log.warn("{}: {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        } else {
            log.info("{}: {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        }

        ErrorResponse errorResponse = ErrorResponse.of(errorCode, message, field, request.getRequestURI(), traceId);
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }

    /**
     * 일반 예외 처리기
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        // 예상치 못한 예외: INTERNAL_SERVER_ERROR로 통일
        return buildErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR,
                "알 수 없는 오류가 발생했습니다.",
                null,
                request,
                e
        );
    }

    /**
     * 비즈니스 로직 예외 처리기
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e, HttpServletRequest request) {
        ErrorCode errorCode = e.getErrorCode();
        List<ErrorResponse.FieldError> fieldErrors = e.getFieldErrors();

        // traceId, logging 처리
        if (errorCode.getHttpStatus().is5xxServerError()) {
            log.error("BusinessException: {}", e.getMessage(), e);
        } else if (errorCode.getHttpStatus().is4xxClientError()) {
            log.warn("BusinessException: {}", e.getMessage(), e);
        } else {
            log.info("BusinessException: {}", e.getMessage(), e);
        }

        // ErrorResponse를 직접 생성
        // (필드 에러가 여러 개 있을 수 있으므로, of(...) 메서드 중 List<FieldError>를 받는 것을 사용)
        String traceId = null; // 필요 시 MDC에서 뽑아서 세팅
        ErrorResponse errorResponse = ErrorResponse.of(errorCode, fieldErrors, request.getRequestURI(), traceId);
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }

    /**
     * MethodArgumentNotValidException 처리 (Bean Validation - @Valid)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();

        log.warn("MethodArgumentNotValidException: {}", e.getMessage());
        String traceId = null;

        ErrorResponse response = ErrorResponse.of(
                ErrorCode.INVALID_REQUEST,
                bindingResult,
                request.getRequestURI(),
                traceId
        );
        return new ResponseEntity<>(response, ErrorCode.INVALID_REQUEST.getHttpStatus());
    }

    /**
     * MissingServletRequestParameterException 처리 (QueryParam 등 누락)
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e, HttpServletRequest request) {
        return buildErrorResponse(
                ErrorCode.INPUT_VALUE_INVALID,
                e.getMessage(),
                e.getParameterName(),
                request,
                e
        );
    }

    /**
     * HttpMessageNotReadableException 처리 (Request Body 변환 실패 등)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e, HttpServletRequest request) {
        return buildErrorResponse(
                ErrorCode.HTTP_MESSAGE_NOT_READABLE,
                e.getMessage(),
                "",
                request,
                e
        );
    }

    /**
     * ConstraintViolationException 처리 (@Validated + @RequestParam, @PathVariable 등에서 위반)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException e, HttpServletRequest request) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        log.warn("ConstraintViolationException: {}", e.getMessage());
        String traceId = null;

        ErrorResponse response = ErrorResponse.of(
                ErrorCode.INPUT_VALUE_INVALID,
                violations,
                request.getRequestURI(),
                traceId
        );
        return new ResponseEntity<>(response, ErrorCode.INPUT_VALUE_INVALID.getHttpStatus());
    }

    /**
     * MissingServletRequestPartException 처리 (Multipart/form-data 전송에서 파트 누락)
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    protected ResponseEntity<ErrorResponse> handleMissingServletRequestPartException(
            MissingServletRequestPartException e, HttpServletRequest request) {
        return buildErrorResponse(
                ErrorCode.INPUT_VALUE_INVALID,
                e.getMessage(),
                e.getRequestPartName(),
                request,
                e
        );
    }

    /**
     * MissingRequestCookieException 처리
     */
    @ExceptionHandler(MissingRequestCookieException.class)
    protected ResponseEntity<ErrorResponse> handleMissingRequestCookieException(
            MissingRequestCookieException e, HttpServletRequest request) {
        return buildErrorResponse(
                ErrorCode.INPUT_VALUE_INVALID,
                e.getMessage(),
                e.getCookieName(),
                request,
                e
        );
    }

    /**
     * IllegalArgumentException 처리
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletRequest request) {
        return buildErrorResponse(
                ErrorCode.BAD_REQUEST,
                e.getMessage(),
                null,
                request,
                e
        );
    }

    /**
     * 추가) HttpRequestMethodNotSupportedException 처리 (예: GET 대신 POST 호출)
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        return buildErrorResponse(
                ErrorCode.METHOD_NOT_ALLOWED,
                e.getMessage(),
                null,
                request,
                e
        );
    }

    /**
     * 추가) HttpMediaTypeNotSupportedException 처리 (지원하지 않는 Content-Type)
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        return buildErrorResponse(
                ErrorCode.UNSUPPORTED_MEDIA_TYPE, // 필요 시 정의
                e.getMessage(),
                null,
                request,
                e
        );
    }

}