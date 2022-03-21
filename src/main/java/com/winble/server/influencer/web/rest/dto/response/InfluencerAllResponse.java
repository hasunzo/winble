package com.winble.server.influencer.web.rest.dto.response;

import com.winble.server.influencer.domain.Influencer;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

// 관리자가 보는 인플루언서 정보를 담은 dto
@Getter
public class InfluencerAllResponse {
    private Long id;
    private String loginId;

    private String nickName;
    private String picture;

    private LocalDateTime joinDt;

    private String status;

    public InfluencerAllResponse(Influencer entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.nickName = entity.getBasicProfile().getNickName();
        this.picture = entity.getBasicProfile().getPicture();
        this.joinDt = entity.getCreateDate();
        this.status = entity.getStatus().name();
    }
}
