package com.winble.server.exception.member;

public class CMemberExistException extends RuntimeException {
    public CMemberExistException() {
        super();
    }

    public CMemberExistException(String msg) {
        super(msg);
    }

    public CMemberExistException(String msg, Throwable t) {
        super(msg, t);
    }
}
