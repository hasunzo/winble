package com.winble.server.application.member.service;

import com.winble.server.adapter.advice.exception.member.CMemberNotFoundException;
import com.winble.server.application.member.model.vo.MemberVO;
import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(Long.valueOf(username)).orElseThrow(CMemberNotFoundException::new);
        return new MemberVO(member);
    }
}
