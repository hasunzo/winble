package com.winble.server.exception.influencer;

public class CInfluencerExistException extends RuntimeException {
    public CInfluencerExistException() {
        super();
    }

    public CInfluencerExistException(String msg) {
        super(msg);
    }

    public CInfluencerExistException(String msg, Throwable t) {
        super(msg, t);
    }
}
