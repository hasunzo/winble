package com.winble.server.member.web.rest.dto.response;

import com.winble.server.member.domain.Influencer;
import lombok.AllArgsConstructor;

import java.util.Date;

public class InfluencerAllResponse {
    private Long id;
    private String loginId;

    private String nickName;
    private String picture;

    private Date joinDt;

    private String status;

    public InfluencerAllResponse(Influencer entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.nickName = entity.getBasicProfile().getNickName();
        this.picture = entity.getBasicProfile().getPicture();
        this.joinDt = entity.getProfileDate().getJoinDt();
        this.status = entity.getStatus().name();
    }
}
