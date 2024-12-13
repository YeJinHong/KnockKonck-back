package com.test.knockknockback.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;
    public static CustomException of(ErrorCode errorCode){
        return new CustomException(errorCode.getHttpStatus(), errorCode.getMessage());
    }
}
