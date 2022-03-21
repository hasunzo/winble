package com.winble.server.influencer.web.rest.dto.response;

import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.profile.Address;
import com.winble.server.influencer.domain.profile.BasicProfile;
import com.winble.server.influencer.domain.profile.InfluencerMedia;
import lombok.Getter;

import java.util.List;

@Getter
public class InfluencerResponse {

    private String signUpType;
    private String loginId;

    private BasicProfile basicProfile;
    private List<Address> addressList;
    private List<InfluencerMedia> influencerMediaList;

    public InfluencerResponse(Influencer entity) {
        this.signUpType = entity.getSignUpType().name();
        this.loginId = entity.getLoginId();

        BasicProfile basicProfile = entity.getBasicProfile();
        this.basicProfile = basicProfile;

        this.addressList = entity.getAddress();
        this.influencerMediaList = entity.getInfluencerMediaList();
    }
}
