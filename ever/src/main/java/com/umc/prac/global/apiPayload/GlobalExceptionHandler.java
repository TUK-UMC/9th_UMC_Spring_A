package com.umc.prac.global.apiPayload;

import com.umc.prac.global.apiPayload.code.GeneralErrorCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity
                .status(GeneralErrorCode.PAGE_INVALID.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.PAGE_INVALID));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(GeneralErrorCode.BAD_REQUEST.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.BAD_REQUEST));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Void>> handleResponseStatus(ResponseStatusException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ApiResponse.onFailure(GeneralErrorCode.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        return ResponseEntity
                .status(GeneralErrorCode.BAD_REQUEST.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.BAD_REQUEST));
    }
}

