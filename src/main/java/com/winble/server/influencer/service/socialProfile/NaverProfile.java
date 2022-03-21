package com.winble.server.influencer.service.socialProfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.enumeration.Role;
import com.winble.server.influencer.domain.enumeration.SignUpType;
import com.winble.server.influencer.domain.profile.BasicProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverProfile implements SocialProfile {

    @SerializedName("id")
    private Long loginId;

    @SerializedName("nickname")
    private String nickName;

    @SerializedName("profile_image")
    private String picture;

    public String getLoginId() {
        return String.valueOf(this.loginId);
    }

    @Override
    public Influencer toInfluencer() {
        BasicProfile basicProfile = new BasicProfile(
                this.nickName,
                this.picture
        );

        return Influencer.builder()
                .loginId(getLoginId())
                .basicProfile(basicProfile)
                .role(Role.USER)
                .signUpType(SignUpType.NAVER)
                .build();
    }
}
