package com.winble.server.influencer.service;

import com.winble.server.common.exception.BizException;
import com.winble.server.common.exception.influencer.AddressCrudErrorCode;
import com.winble.server.common.exception.influencer.InfluencerCrudErrorCode;
import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.profile.Address;
import com.winble.server.influencer.repository.AddressRepository;
import com.winble.server.influencer.repository.InfluencerRepository;
import com.winble.server.influencer.web.rest.dto.request.AddressAddRequest;
import com.winble.server.influencer.web.rest.dto.request.AddressUpdateRequest;
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
    private final AddressRepository addressRepository;

    // 전체 인플루언서 회원 리스트를 반환하는 메소드
    public List<Influencer> findAllInfluencer() {
        return influencerRepository.findAll();
    }

    // 회원 정보를 반환하는 메소드
    @Transactional
    public Influencer findInfluencerByLoginId(String influencerId) {
        return findInfluencer(influencerId);
    }

    // 인플루언서 기본정보 수정 메소드
    @Transactional
    public Influencer updateInfluencer(String influencerId, InfluencerUpdateRequest influencerUpdateRequest) {
        Influencer influencer = findInfluencer(influencerId);

        influencer.updateBasicProfile(influencerUpdateRequest);

        return influencer;
    }

    // 인플루언서 전체 주소 조회 메소드
    @Transactional
    public List<Address> findAllAddressByInfluencer(String influencerId) {
        Influencer influencer = findInfluencer(influencerId);
        return addressRepository.findAllByInfluencer(influencer);
    }

    // 인플루언서 주소 추가 메소드
    @Transactional
    public Address addAddress(String influencerId, AddressAddRequest addressRequest) {
        Influencer influencer = findInfluencer(influencerId);

        Address address = addressRequest.toAddress(influencer);

        return addressRepository.save(address);
    }

    // 인플루언서 주소 변경 메소드
    @Transactional
    public Address updateAddress(String influencerId, AddressUpdateRequest addressUpdateRequest) {
        Influencer influencer = findInfluencer(influencerId);
        Address address = findAddress(addressUpdateRequest.getId());
        // 인플루언서의 주소가 맞는 지 확인
        if (address.isNotRegisteredBy(influencer)) {
            throw new BizException(AddressCrudErrorCode.ADDRESS_NOT_BELONG_TO_INFLUENCER);
        }
        address.updateAddress(addressUpdateRequest);
        return address;
    }

    // 인플루언서 주소 삭제 메소드
    @Transactional
    public void deleteAddress(String influencerId, Long addressId) {
        Influencer influencer = findInfluencer(influencerId);
        Address address = findAddress(addressId);
        influencer.deleteAddress(address);
        addressRepository.delete(address);
    }

    // 인플루언서 반환
    public Influencer findInfluencer(String influencerId) {
        return influencerRepository.findById(Long.valueOf(influencerId))
                .orElseThrow(() -> new BizException(InfluencerCrudErrorCode.INFLUENCER_NOT_FOUND));
    }

    // 인플루언서 주소 반환
    public Address findAddress(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new BizException(AddressCrudErrorCode.ADDRESS_NOT_FOUND));
    }

}
