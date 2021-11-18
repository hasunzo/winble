package com.winble.server.domain.model.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN", "관리자"),
    ADVERTISER("ROLE_ADVERTISER", "광고주"),
    INFLUENCER("ROLE_INFLUENCER", "인플루언서");

    private final String key;
    private final String title;
}
