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
public class KakaoProfile implements SocialProfile {

    @JsonProperty("id")
    private String loginId;

    private Properties properties;

    @Getter
    @Setter
    @ToString
    private static class Properties {
        @JsonProperty("nickname")
        private String nickName;
        @JsonProperty("thumbnail_image")
        private String picture;
    }

    public String getLoginId() {
        return this.loginId;
    }

    @Override
    public Influencer toInfluencer() {
        BasicProfile basicProfile = new BasicProfile(
                this.properties.getNickName(),
                this.properties.getPicture()
        );

        return Influencer.builder()
                .loginId(this.loginId)
                .basicProfile(basicProfile)
                .role(Role.USER)
                .signUpType(SignUpType.KAKAO)
                .build();
    }
}
