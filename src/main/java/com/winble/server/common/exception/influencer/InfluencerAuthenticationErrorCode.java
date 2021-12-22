package com.winble.server.common.exception.influencer;

import com.winble.server.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum InfluencerAuthenticationErrorCode implements ErrorCode {
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "해당 리소스에 접근하기 위한 권한이 없습니다.");

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
        return null;
    }

    @Override
    public Integer getErrorCode() {
        return null;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}
