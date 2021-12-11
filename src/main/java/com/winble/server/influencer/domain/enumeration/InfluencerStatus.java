package com.winble.server.influencer.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InfluencerStatus {
    ACTIVE("활성"),
    REST("이용제한"),
    BAN("영구정지");

    private final String status;
}
