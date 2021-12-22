package com.winble.server.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getMsg();
    Integer getErrorCode();
    HttpStatus getHttpStatus();
}