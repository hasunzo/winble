package com.winble.server.exception.influencer;

// 회원을 찾을 수 없을때 발생하는 익셉션
public class CInfluencerNotFoundException extends RuntimeException {

    public CInfluencerNotFoundException() {
    }

    public CInfluencerNotFoundException(String msg) {
        super(msg);
    }

    public CInfluencerNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
