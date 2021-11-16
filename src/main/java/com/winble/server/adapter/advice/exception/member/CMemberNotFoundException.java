package com.winble.server.adapter.advice.exception.member;

// 회원을 찾을 수 없을때 발생하는 익셉션
public class CMemberNotFoundException extends RuntimeException {

    public CMemberNotFoundException() {
    }

    public CMemberNotFoundException(String msg) {
        super(msg);
    }

    public CMemberNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
