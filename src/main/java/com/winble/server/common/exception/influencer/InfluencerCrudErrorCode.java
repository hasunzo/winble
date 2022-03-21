package com.winble.server.common.exception.influencer;

import com.winble.server.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum InfluencerCrudErrorCode implements ErrorCode {
    INFLUENCER_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다."),
    INFLUENCER_EXIST(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), "이미 존재하는 회원입니다."),
    EMAIL_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "이메일 또는 비밀번호가 올바르지 않습니다."),
    NOT_MATCHED_PASSWORD(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "현재 비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String msg;

    InfluencerCrudErrorCode(HttpStatus httpStatus, Integer errorCode, String msg) {
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
