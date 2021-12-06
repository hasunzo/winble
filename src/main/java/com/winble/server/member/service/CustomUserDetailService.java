package com.winble.server.member.service;

import com.winble.server.exception.member.CMemberNotFoundException;
import com.winble.server.member.web.rest.dto.response.UserDetailsResponse;
import com.winble.server.member.domain.Member;
import com.winble.server.member.repository.MemberRepository;
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
        return new UserDetailsResponse(member);
    }
}
