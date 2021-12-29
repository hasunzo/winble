package com.winble.server.influencer.web.rest.dto.response;

import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.profile.BasicProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class InfluencerResponse {

    private String signUpType;
    private String loginId;

    private String maketingConsent;
    private String name;
    private String nickName;
    private String picture;
    private String phoneNumber;

    public InfluencerResponse(Influencer entity) {
        this.signUpType = entity.getSignUpType().name();
        this.loginId = entity.getLoginId();

        BasicProfile basicProfile = entity.getBasicProfile();

        this.maketingConsent = basicProfile.getMaketingConsent().name();
        this.name = basicProfile.getName();
        this.nickName = basicProfile.getNickName();
        this.picture = basicProfile.getPicture();
        this.phoneNumber = basicProfile.getPhoneNumber();
    }
}
