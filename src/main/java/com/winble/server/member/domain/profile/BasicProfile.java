package com.winble.server.member.domain.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class BasicProfile {
    @Column(length = 30)
    private String name;

    private String nickName;

    @Column(length = 20, unique = true)
    private String phoneNumber;

    private String picture;

    // 자사 서비스 회원가입시 picture는 default 값으로 등록된다.
    public BasicProfile(String nickName) {
        this(nickName, "default");
    }

    // 소셜 회원 가입 시 소셜 서비스에서 받은 picture이 등록된다.
    public BasicProfile(String nickName, String picture) {
        this.nickName = nickName;
        this.picture = picture;
    }

    public BasicProfile() {
    }
}
