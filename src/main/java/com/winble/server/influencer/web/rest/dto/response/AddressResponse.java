package com.winble.server.influencer.web.rest.dto.response;

import com.winble.server.influencer.domain.profile.Address;
import lombok.Getter;

@Getter
public class AddressResponse {
    private String title;
    private String recipient;
    private String zipCode;
    private String addressFirst;
    private String addressLast;

    public AddressResponse(Address address) {
        this.title = address.getTitle();
        this.recipient = address.getRecipient();
        this.zipCode = address.getZipCode();
        this.addressFirst = address.getAddressFirst();
        this.addressLast = address.getAddressLast();
    }
}
