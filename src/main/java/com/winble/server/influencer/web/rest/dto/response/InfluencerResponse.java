package com.winble.server.influencer.web.rest.dto.response;

import com.winble.server.influencer.domain.Influencer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class InfluencerResponse {
    private String loginId;
    private String maketingConsent;

    private String name;
    private String nickName;
    private String picture;
    private String phoneNumber;

    public InfluencerResponse(Influencer entity) {
        this.loginId = entity.getLoginId();
        this.nickName = entity.getBasicProfile().getNickName();
        this.picture = entity.getBasicProfile().getPicture();
        this.maketingConsent = entity.getMaketingConsent();
    }
}
