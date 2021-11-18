package com.winble.server.application.member.service;

import com.winble.server.adapter.advice.exception.member.CEmailLoginFailedException;
import com.winble.server.adapter.config.security.JwtTokenProvider;
import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.entity.Role;
import com.winble.server.domain.model.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    // 자사 서비스 로그인 메소드
    public String login(String email, String password) {
        // email로 회원을 찾는다.
        // 아이디 혹은 비밀번호가 맞지 않다면 CEmailSigninFailedException이 발생한다.
        Member member = memberRepository.findByMemberEmail(email).orElseThrow(CEmailLoginFailedException::new);
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CEmailLoginFailedException();
        }

        // 토큰을 발급한다.
        return jwtTokenProvider.createToken(String.valueOf(member.getMemberId()), member.getRole().getKey());
    }

    // 자사 서비스 회원가입
    public void join(String email, String password, String nickName) {
        memberRepository.save(Member.builder()
                .memberEmail(email)
                .password(passwordEncoder.encode(password))
                .nickName(nickName)
                .role(Role.INFLUENCER)
                .build());
    }
}
