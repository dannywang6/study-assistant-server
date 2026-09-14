package com.studyassistant.exception;


import com.studyassistant.common.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ApiResponse<Void> handleException(Exception e) {
        return ApiResponse.error(500, e.getMessage());
    }
}
