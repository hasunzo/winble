package com.winble.server.influencer.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * 동의 여부를 확인하는 enum class
 *
 */
@Getter
@RequiredArgsConstructor
public enum ConsentStatus {
    Y(true),
    N(false);

    private final boolean bcs;
}
