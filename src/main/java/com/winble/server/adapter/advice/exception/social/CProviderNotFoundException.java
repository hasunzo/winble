package com.winble.server.adapter.advice.exception.social;

// 적절하지 않은 provider(소셜) 넘겼을 때 발생하는 익셉션
public class CProviderNotFoundException extends RuntimeException {
    public CProviderNotFoundException() {
        super();
    }

    public CProviderNotFoundException(String msg) {
        super(msg);
    }

    public CProviderNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
