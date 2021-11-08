package com.winble.server.adapter.advice.exception;

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
