package com.winble.server.common.exception.social;

import com.winble.server.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum SocialLoginErrorCode implements ErrorCode {
    COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), "통신 중 오류가 발생했습니다."),
    PROVIDER_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "올바르지 않은 소셜 타입입니다.");

    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String msg;

    SocialLoginErrorCode(HttpStatus httpStatus, int errorCode, String msg) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
