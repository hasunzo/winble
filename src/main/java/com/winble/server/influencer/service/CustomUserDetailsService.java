package com.winble.server.influencer.service;

import com.winble.server.common.exception.BizException;
import com.winble.server.common.exception.influencer.InfluencerCrudErrorCode;
import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.repository.InfluencerRepository;
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
        Influencer user = influencerRepository.findById(Long.valueOf(username)).orElseThrow(() -> new BizException(InfluencerCrudErrorCode.INFLUENCER_NOT_FOUND));
        return new CustomUserDetails(user);
    }
}
