package com.winble.server.influencer.domain.profile;

import com.winble.server.influencer.domain.Influencer;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

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

    public Address() {

    }

    public void setInfluencer(Influencer influencer) {
        if (Objects.nonNull(this.influencer)) {
            this.influencer.getAddress().remove(this);
        }
        this.influencer = influencer;
        influencer.getAddress().add(this);
    }
}
