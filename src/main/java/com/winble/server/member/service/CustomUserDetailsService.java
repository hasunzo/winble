package com.winble.server.member.service;

import com.winble.server.exception.member.CMemberNotFoundException;
import com.winble.server.member.domain.Influencer;
import com.winble.server.member.repository.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final InfluencerRepository influencerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Influencer user = influencerRepository.findById(Long.valueOf(username)).orElseThrow(CMemberNotFoundException::new);
        return new CustomUserDetails(user);
    }
}
