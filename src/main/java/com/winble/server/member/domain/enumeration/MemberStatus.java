package com.winble.server.member.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {
    ACTIVE("활성"),
    REST("이용제한"),
    BAN("영구정지");

    private final String status;
}
