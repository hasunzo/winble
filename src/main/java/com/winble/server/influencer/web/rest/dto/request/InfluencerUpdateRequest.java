package com.winble.server.influencer.web.rest.dto.request;

import com.winble.server.influencer.domain.profile.BasicProfile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InfluencerUpdateRequest {
    @ApiModelProperty(value = "이름", required = true)
    private final String name;

    @ApiModelProperty(value = "닉네임", required = true)
    private final String nickName;

    @ApiModelProperty(value = "핸드폰번호", required = true)
    private final String phoneNumber;

    @ApiModelProperty(value = "마케팅수신동의", required = true)
    private final String maketingConsent;

    public BasicProfile toBasicProfile() {
        return new BasicProfile(
                name, nickName, phoneNumber, maketingConsent
        );
    }

}
