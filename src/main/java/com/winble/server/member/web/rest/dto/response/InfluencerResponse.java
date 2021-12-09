package com.winble.server.member.web.rest.dto.response;

import com.winble.server.member.domain.Influencer;

public class InfluencerResponse {
    private String loginId;
    private String maketingConsent;

    private String name;
    private String nickname;
    private String picture;
    private String phoneNumber;

    public InfluencerResponse(Influencer entity) {
        this.loginId = entity.getLoginId();
        this.nickname = entity.getBasicProfile().getNickName();
        this.picture = entity.getBasicProfile().getPicture();
        this.maketingConsent = entity.getMaketingConsent();
    }
}
