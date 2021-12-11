package com.winble.server.influencer.service.socialProfile;

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
public class KakaoProfile implements SocialProfile {

    @SerializedName("id")
    private Long loginId;

    @SerializedName("kakao_account")
    private KakaoAccount kakaoAccount;

    public String getLoginId() {
        return String.valueOf(this.loginId);
    }

    @Override
    public Influencer toInfluencer() {
        BasicProfile basicProfile = new BasicProfile(
                this.kakaoAccount.getProfile().getNickName(),
                this.kakaoAccount.getProfile().getPicture()
        );

        return Influencer.builder()
                .loginId(this.getLoginId())
                .basicProfile(basicProfile)
                .role(Role.USER)
                .signUpType(SignUpType.KAKAO)
                .build();
    }
}
