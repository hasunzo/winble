package com.winble.server.influencer.domain.profile;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
public class BasicProfile {
    @Column(length = 30)
    private String name;

    @NotNull
    @Column(unique = true)
    private String nickName;

    @Column(length = 20, unique = true)
    private String phoneNumber;

    private String picture;

    // 자사 서비스 회원가입시 picture는 default 값으로 등록된다.
    public BasicProfile(String nickName) {
        this(nickName, "default.png");
    }

    // 소셜 회원 가입 시 소셜 서비스에서 받은 picture이 등록된다.
    public BasicProfile(String nickName, String picture) {
        this.nickName = nickName;
        this.picture = picture;
    }

    public BasicProfile() {
    }
}
