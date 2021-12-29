package com.winble.server.influencer.web.rest.dto.response;

import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.profile.BasicProfile;
import lombok.Getter;

@Getter
public class InfluencerUpdateResponse {
    private String name;
    private String nickName;
    private String phoneNumber;
    private String maketingConsent;

    public InfluencerUpdateResponse(Influencer influencer) {
        BasicProfile basicProfile = influencer.getBasicProfile();
        this.name = basicProfile.getName();
        this.nickName = basicProfile.getNickName();
        this.phoneNumber = basicProfile.getPhoneNumber();
        this.maketingConsent = basicProfile.getMaketingConsent().name();
    }
}
