package com.winble.server.influencer.repository;

import com.winble.server.common.exception.BizException;
import com.winble.server.common.exception.influencer.InfluencerCrudErrorCode;
import com.winble.server.dummy.influencer.InfluencerDummy;
import com.winble.server.influencer.domain.Influencer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class InfluencerRepositoryTests {

    @Autowired
    private InfluencerRepository influencerRepository;

    private final InfluencerDummy influencerDummy = InfluencerDummy.getInstance();

    @BeforeEach
    public void setUp() {
        influencerRepository.save(influencerDummy.toEntity());
    }

    @Test
    @DisplayName("회원의 로그인 아이디로 회원정보를 조회합니다.")
    public void findMembersByEmail() {
        Influencer influencer = influencerRepository.findByLoginId(influencerDummy.getLoginId())
                .orElseThrow(() -> new BizException(InfluencerCrudErrorCode.INFLUENCER_NOT_FOUND));

        assertThat(influencer.getLoginId(), is(influencerDummy.getLoginId()));
        assertThat(influencer.getSignUpType(), is(influencerDummy.getSignUpType()));
        assertThat(influencer.getRole(), is(influencerDummy.getRole()));
        assertThat(influencer.getStatus(), is(influencerDummy.getStatus()));
    }

    @Test
    @DisplayName("회원의 로그인 아이디와 가입유형으로 회원정보를 조회합니다.")
    public void findByLoginIdAndSignUpType() {
        Influencer influencer = influencerRepository
                .findByLoginIdAndSignUpType(influencerDummy.getLoginId(), influencerDummy.getSignUpType())
                .orElseThrow(() -> new BizException(InfluencerCrudErrorCode.INFLUENCER_NOT_FOUND));
        assertThat(influencer.getSignUpType(), is(influencerDummy.getSignUpType()));
    }

    @Test
    @DisplayName("회원 로그인 아이디와 가입유형으로 존재여부를 확인합니다.")
    public void existByLoginIdAndSignUpType() {
        assertThat(true, is(influencerRepository
                .existsByLoginIdAndSignUpType(influencerDummy.getLoginId(), influencerDummy.getSignUpType())));
    }
}
