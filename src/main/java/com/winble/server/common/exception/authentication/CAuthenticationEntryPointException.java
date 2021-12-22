package com.winble.server.common.exception.authentication;

// jwt 토큰 없이 api 호출 했을 때
// 형식에 맞지 않거나 만료된 jwt 토큰으로 api 호출 했을 때 발생하는 익셉션
public class CAuthenticationEntryPointException extends RuntimeException {
    public CAuthenticationEntryPointException() {
        super();
    }

    public CAuthenticationEntryPointException(String msg) {
        super(msg);
    }

    public CAuthenticationEntryPointException(String msg, Throwable t) {
        super(msg, t);
    }
}
