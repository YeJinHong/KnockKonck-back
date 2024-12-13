package com.test.knockknockback.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 클라이언트 요청이 유효하지 않아 더 이상 작업을 진행하지 않는 경우 */
    INVALID_REQUEST(BAD_REQUEST, "올바르지 않은 요청입니다."),
    BIZES_NOT_EXIST(BAD_REQUEST, "해당하는 bizes 정보가 없습니다."),

    /* 401 Unauthorized : 클라이언트가 요청한 리소스에 대한 인증 정보가 없거나 잘못된 경우 */


    /* 500 INTERNAL_SERVER_ERROR : 서버 내부의 exception 발생으로 응답을 줄 수 없는 경우 */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생하였습니다. 관리자에게 문의해 주세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
