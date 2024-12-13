package com.test.knockknockback.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class BaseExceptionResponse {
    private final int code;
    private final String message;

    public static BaseExceptionResponse of(CustomException e){
        return new BaseExceptionResponse(
                e.getHttpStatus().value(),
                e.getMessage()
        );
    }
}
