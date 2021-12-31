package com.winble.server.influencer.web.rest.dto.request;

import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.profile.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddAddressRequest {
    private final String zipCode;
    private final String state;
    private final String city;
    private final String detailAddress;

    public Address toAddress(Influencer influencer) {
        return Address.builder()
                .zipCode(zipCode)
                .state(state)
                .city(city)
                .detailAddress(detailAddress)
                .influencer(influencer)
                .build();
    }
}
