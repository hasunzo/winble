package com.winble.server.influencer.domain.profile;

import com.winble.server.influencer.domain.enumeration.ConsentStatus;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * 인플루언서 기본 정보 엔티티
 *
 */
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

    private ConsentStatus maketingConsent;

    // 자사 서비스 회원가입시 picture는 default 값으로 등록된다.
    public BasicProfile(String nickName) {
        this(nickName, "default.png");
    }

    // 소셜 회원 가입 시 소셜 서비스에서 받은 picture이 등록된다.
    public BasicProfile(String nickName, String picture) {
        this.nickName = nickName;
        this.picture = picture;
        this.maketingConsent = ConsentStatus.N;
    }

    public BasicProfile(String name, String nickName, String phoneNumber, String maketingConsent) {
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.maketingConsent = ConsentStatus.valueOf(maketingConsent);
    }

    public BasicProfile() {
    }

    public void update(BasicProfile basicProfile) {
        this.name = basicProfile.name;
        this.nickName = basicProfile.nickName;
        this.phoneNumber = basicProfile.phoneNumber;
        this.maketingConsent = basicProfile.maketingConsent;
    }

    public void pictureUpdate(String picture){
        this.picture = picture;
    }
}
