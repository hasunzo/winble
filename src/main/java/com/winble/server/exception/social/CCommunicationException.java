package com.winble.server.exception.social;

// 소셜 서비스 통신 중 오류 상황 시 발생하는 익셉션
public class CCommunicationException extends RuntimeException {
    public CCommunicationException() {
        super();
    }

    public CCommunicationException(String msg) {
        super(msg);
    }

    public CCommunicationException(String msg, Throwable y) {
        super(msg, y);
    }
}
