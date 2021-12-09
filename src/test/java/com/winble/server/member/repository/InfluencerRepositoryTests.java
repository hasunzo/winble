package com.winble.server.member.repository;

import com.winble.server.member.domain.enumeration.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class InfluencerRepositoryTests {

    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 이메일로 회원을 찾음을 테스트
    @Test
    @DisplayName("회원의 이메일로 회원정보를 조회합니다.")
    public void findMembersByEmail() {
        // given
        String memberLoginId = "test1@test.com";
        String nickName = "홍길";
        influencerRepository.save(Member.builder()
                    .memberLoginId(memberLoginId)
                    .nickName(nickName)
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.INFLUENCER)
                    .build());

        // when
        Optional<Member> member = influencerRepository.findByMemberLoginId(memberLoginId);

        // then
        assertThat(member.get().getMemberLoginId(), is(memberLoginId));
    }
}
