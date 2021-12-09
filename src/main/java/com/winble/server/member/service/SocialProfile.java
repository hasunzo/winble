package com.winble.server.member.service;

import com.winble.server.member.domain.Influencer;

public interface SocialProfile {
    String getLoginId();
    Influencer toInfluencer();
}
