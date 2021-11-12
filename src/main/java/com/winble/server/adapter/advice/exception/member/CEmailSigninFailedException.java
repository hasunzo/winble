package com.winble.server.adapter.advice.exception.member;

public class CEmailSigninFailedException extends RuntimeException {
    public CEmailSigninFailedException() {
    }

    public CEmailSigninFailedException(String msg) {
        super(msg);
    }

    public CEmailSigninFailedException(String msg, Throwable t) {
        super(msg, t);
    }
}
