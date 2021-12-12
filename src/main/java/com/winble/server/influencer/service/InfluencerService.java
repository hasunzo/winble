package com.winble.server.influencer.service;

import com.winble.server.exception.influencer.CInfluencerNotFoundException;
import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.repository.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InfluencerService {
    private final InfluencerRepository influencerRepository;

    // 전체 인플루언서 회원 리스트를 반환하는 메소드
    public List<Influencer> findAllInfluencer() {
        return influencerRepository.findAll();
    }

    // 회원 이메일로 회원 정보를 반환하는 메소드
    public Influencer findInfluencerByLoginId(String loginId) {
        // Exception 발생시 CMemberNotFoundException 생성
        return influencerRepository.findByLoginId(loginId).orElseThrow(CInfluencerNotFoundException::new);
    }
}
