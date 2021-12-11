package com.winble.server.influencer.service.socialProfile;

import com.winble.server.influencer.domain.Influencer;

public interface SocialProfile {
    String getLoginId();
    Influencer toInfluencer();
}
