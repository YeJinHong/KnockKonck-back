package com.test.knockknockback.util.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.test.knockknockback.util.exception.ErrorCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseExceptionResponse> handleCustomException(CustomException ce){
        return ResponseEntity
                .status(ce.getHttpStatus())
                .body(BaseExceptionResponse.of(ce));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseExceptionResponse> handleException(final Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(BaseExceptionResponse.of(CustomException.of(INTERNAL_SERVER_ERROR)));
    }
}
