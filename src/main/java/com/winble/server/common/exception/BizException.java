package com.winble.server.common.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BizException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final Integer errorCode;
    private final String message;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMsg(), new Throwable(errorCode.getHttpStatus().getReasonPhrase()));
        this.httpStatus = errorCode.getHttpStatus();
        this.errorCode = errorCode.getErrorCode();
        this.message = errorCode.getMsg();
    }
}
