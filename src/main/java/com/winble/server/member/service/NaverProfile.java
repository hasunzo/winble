package com.winble.server.member.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.winble.server.member.domain.Influencer;
import com.winble.server.member.domain.enumeration.Role;
import com.winble.server.member.domain.enumeration.SignUpType;
import com.winble.server.member.domain.profile.BasicProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverProfile implements SocialProfile {

    @JsonProperty("id")
    private String loginId;

    @JsonProperty("nickname")
    private String nickName;

    @JsonProperty("profile_image")
    private String picture;

    public String getLoginId() {
        return this.loginId;
    }

    @Override
    public Influencer toInfluencer() {
        BasicProfile basicProfile = new BasicProfile(
                this.nickName,
                this.picture
        );

        return Influencer.builder()
                .loginId(this.loginId)
                .basicProfile(basicProfile)
                .role(Role.USER)
                .signUpType(SignUpType.NAVER)
                .build();
    }
}
