package com.winble.server.dummy.influencer;

import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.enumeration.InfluencerStatus;
import com.winble.server.influencer.domain.enumeration.Role;
import com.winble.server.influencer.domain.enumeration.SignUpType;
import com.winble.server.influencer.domain.profile.BasicProfile;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * 테스트 환경에서 사용하는
 * 인플루언서 회원의 더미데이터
 *
 */
public class InfluencerDummy {
    private final String loginId = "test01@test.com";
    private final String password = "password1234";
    private final BasicProfile basicProfile = new BasicProfile("닉네임");
    private final SignUpType signUpType = SignUpType.WINBLE;
    private final Role role = Role.USER;
    private final InfluencerStatus status = InfluencerStatus.ACTIVE;

    private static final InfluencerDummy instance = null;

    private InfluencerDummy() {}

    public static InfluencerDummy getInstance() {
        if (instance == null) {
            return new InfluencerDummy();
        }
        return instance;
    }

    public String getLoginId() {
        return loginId;
    }

    public BasicProfile getBasicProfile() {
        return basicProfile;
    }

    public SignUpType getSignUpType() {
        return signUpType;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public InfluencerStatus getStatus() {
        return status;
    }

    public Influencer toEntity() {
        return Influencer.builder()
                .loginId(loginId)
                .password(password)
                .basicProfile(basicProfile)
                .signUpType(signUpType)
                .role(role)
                .status(status)
                .build();
    }
}
