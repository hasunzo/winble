package com.winble.server.influencer.domain.profile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.web.rest.dto.request.AddressUpdateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;                // 주소 고유값

    private String title;           // 배송지명
    private String recipient;       // 수령인
    private String zipCode;         // 우편번호
    private String addressFirst;    // 상세주소
    private String addressLast;     // 추가주소

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INFLUENCER_ID")
    private Influencer influencer;  // 인플루언서 참조

    @Builder
    public Address(String title, String recipient, String zipCode, String addressFirst, String addressLast, Influencer influencer) {
        this.title = title;
        this.recipient = recipient;
        this.zipCode = zipCode;
        this.addressFirst = addressFirst;
        this.addressLast = addressLast;
        if (Objects.nonNull(influencer)) {
            setInfluencer(influencer);
        }
    }

    public void setInfluencer(Influencer influencer) {
        if (Objects.nonNull(this.influencer)) {
            this.influencer.getAddress().remove(this);
        }
        this.influencer = influencer;
        influencer.getAddress().add(this);
    }

    public void updateAddress(AddressUpdateRequest request) {
        this.title = request.getTitle();
        this.recipient = request.getRecipient();
        this.zipCode = request.getZipCode();
        this.addressFirst = request.getAddressFirst();
        this.addressLast = request.getAddressLast();
    }

    public boolean isNotRegisteredBy(Influencer influencer) {
        return !this.influencer.equals(influencer);
    }
}
