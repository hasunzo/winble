package com.winble.server.adapter.config.security;

import com.winble.server.adapter.advice.exception.member.CMemberNotFoundException;
import com.winble.server.domain.model.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 *
 * 토큰에 세팅된 유저 정보로 회원정보를 조회하는 UserDetailsService를 재정의한다.
 *
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) {
        return (UserDetails) memberRepository.findById(Long.valueOf(userPk)).orElseThrow(CMemberNotFoundException::new);
    }
}
