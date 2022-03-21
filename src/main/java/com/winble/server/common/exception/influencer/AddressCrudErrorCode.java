package com.winble.server.common.exception.influencer;

import com.winble.server.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum AddressCrudErrorCode implements ErrorCode {
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "존재하지 않는 주소입니다."),
    ADDRESS_NOT_BELONG_TO_INFLUENCER(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "해당 인플루언서의 주소가 아닙니다.");

    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String msg;

    AddressCrudErrorCode(HttpStatus httpStatus, Integer errorCode, String msg) {
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
