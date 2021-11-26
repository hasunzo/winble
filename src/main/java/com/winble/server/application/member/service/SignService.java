package com.winble.server.application.member.service;

import com.winble.server.adapter.advice.exception.member.CEmailLoginFailedException;
import com.winble.server.adapter.advice.exception.member.CMemberExistException;
import com.winble.server.adapter.advice.exception.member.CMemberNotFoundException;
import com.winble.server.adapter.config.security.JwtTokenProvider;
import com.winble.server.application.member.model.dto.MemberInfoResponse;
import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.entity.Role;
import com.winble.server.domain.model.member.entity.SocialType;
import com.winble.server.domain.model.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SignService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final SocialService socialService;


    // 자사 서비스 로그인 메소드
    public String login(String memberLoginId, String password) {
        // email로 회원을 찾는다.
        // 아이디 혹은 비밀번호가 맞지 않다면 CEmailSigninFailedException이 발생한다.
        Member member = memberRepository.findByMemberLoginId(memberLoginId).orElseThrow(CEmailLoginFailedException::new);
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CEmailLoginFailedException();
        }

        // 토큰을 발급한다.
        return jwtTokenProvider.createToken(String.valueOf(member.getMemberId()), member.getRole().getKey());
    }

    // 자사 서비스 회원가입
    public void join(String memberLoginId, String password, String nickName) {
        Optional<Member> member = memberRepository.findByMemberLoginId(memberLoginId);
        if (member.isPresent()) {
            throw new CMemberExistException();
        }
        memberRepository.save(Member.builder()
                .memberLoginId(memberLoginId)
                .password(passwordEncoder.encode(password))
                .nickName(nickName)
                .role(Role.INFLUENCER)
                .build());
    }

    // 소셜 서비스 로그인
    public String loginBySocial(String accessToken, String socialType) {
        // 소셜 토큰으로 소셜 회원의 프로필을 가져온다.
        MemberInfoResponse memberInfoResponse = socialService.getSocialProfile(accessToken, socialType);
        Member member = memberRepository.findByMemberLoginIdAndSocialType(String.valueOf(memberInfoResponse.getId()), SocialType.valueOf(socialType)).orElseThrow(CMemberNotFoundException::new);

        // 토큰 발급
        return jwtTokenProvider.createToken(String.valueOf(member.getMemberId()), member.getRole().getKey());
    }

    // 소셜 서비스 회원가입
    public void joinBySocial(String accessToken, String socialType) {
        MemberInfoResponse memberInfoResponse = socialService.getSocialProfile(accessToken, socialType);
        Optional<Member> member = memberRepository.findByMemberLoginIdAndSocialType(String.valueOf(memberInfoResponse.getId()), SocialType.valueOf(socialType));
        // 이미 가입한 회원 예외 처리
        if (member.isPresent()) {
            throw new CMemberExistException();
        }
        memberRepository.save(memberInfoResponse.toMember());
    }


}
