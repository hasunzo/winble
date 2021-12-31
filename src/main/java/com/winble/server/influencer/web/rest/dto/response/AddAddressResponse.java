package com.winble.server.influencer.web.rest.dto.response;

import com.winble.server.influencer.domain.profile.Address;
import lombok.Getter;

@Getter
public class AddAddressResponse {
    private String zipCode;
    private String state;
    private String city;
    private String detailAddress;

    public AddAddressResponse(Address address) {
        this.zipCode = address.getZipCode();
        this.state = address.getState();
        this.city = address.getCity();
        this.detailAddress = address.getDetailAddress();
    }
}
