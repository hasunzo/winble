package com.winble.server.exception.member;

// 아이디 혹은 비밀번호가 일치않을 시 발생하는 익셉션
public class CEmailLoginFailedException extends RuntimeException {
    public CEmailLoginFailedException() {
    }

    public CEmailLoginFailedException(String msg) {
        super(msg);
    }

    public CEmailLoginFailedException(String msg, Throwable t) {
        super(msg, t);
    }
}
