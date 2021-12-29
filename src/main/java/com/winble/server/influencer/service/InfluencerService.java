package com.winble.server.influencer.service;

import com.winble.server.common.exception.BizException;
import com.winble.server.common.exception.influencer.InfluencerCrudErrorCode;
import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.profile.BasicProfile;
import com.winble.server.influencer.repository.InfluencerRepository;
import com.winble.server.influencer.web.rest.dto.request.InfluencerUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class InfluencerService {
    private final InfluencerRepository influencerRepository;

    // 전체 인플루언서 회원 리스트를 반환하는 메소드
    public List<Influencer> findAllInfluencer() {
        return influencerRepository.findAll();
    }

    @Transactional
    // 회원 정보를 반환하는 메소드
    public Influencer findInfluencerByLoginId(String influencerId) {
        return influencerRepository.findById(Long.valueOf(influencerId))
                .orElseThrow(() -> new BizException(InfluencerCrudErrorCode.INFLUENCER_NOT_FOUND));
    }

    @Transactional
    public Influencer updateInfluencer(String influencerId, InfluencerUpdateRequest influencerUpdateRequest) {
        Influencer influencer = influencerRepository.findById(Long.valueOf(influencerId))
                .orElseThrow(() -> new BizException(InfluencerCrudErrorCode.INFLUENCER_NOT_FOUND));

        influencer.updateBasicProfile(influencerUpdateRequest);

        return influencer;
    }
}
