package com.winble.server.member;

import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.entity.Role;
import com.winble.server.domain.model.member.repository.MemberRepository;
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
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 이메일로 회원을 찾음을 테스트
    @Test
    public void 이메일로_회원을_찾는다() {
        // given
        String memberEmail = "test1@test.com";
        String nickName = "홍길";
        memberRepository.save(Member.builder()
                    .memberEmail(memberEmail)
                    .nickName(nickName)
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.INFLUENCER)
                    .build());

        // when
        Optional<Member> member = memberRepository.findByMemberEmail(memberEmail);

        // then
        assertThat(member.get().getMemberEmail(), is(memberEmail));
    }
}
