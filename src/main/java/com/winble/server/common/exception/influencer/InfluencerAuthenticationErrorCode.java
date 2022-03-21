package com.winble.server.common.exception.influencer;

import com.winble.server.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum InfluencerAuthenticationErrorCode implements ErrorCode {
    NOT_CERTIFIED(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "인증되지 않은 사용자입니다."),
    NOT_GRANTED(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value(), "보유한 권한으로 접근할 수 없는 리소스 입니다.");

    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String msg;

    InfluencerAuthenticationErrorCode(HttpStatus httpStatus, int errorCode, String msg) {
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
