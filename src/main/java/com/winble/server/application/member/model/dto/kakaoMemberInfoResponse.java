package com.winble.server.application.member.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.entity.Role;
import com.winble.server.domain.model.member.entity.SocialType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class kakaoMemberInfoResponse implements MemberInfoResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String memberName;

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

    public String getId() {
        return String.valueOf(this.id);
    }

    @Override
    public Member toMember() {
        return Member.builder()
                .memberLoginId(String.valueOf(this.id))
                .memberName(this.memberName)
                .nickName(this.properties.nickName)
                .role(Role.INFLUENCER)
                .picture(this.properties.picture)
                .socialType(SocialType.KAKAO)
                .build();
    }
}
