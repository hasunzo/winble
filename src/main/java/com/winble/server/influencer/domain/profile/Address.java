package com.winble.server.influencer.domain.profile;

import com.winble.server.influencer.domain.Influencer;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;    // 주소 고유값

    private String zipCode; // 우편번호
    private String state;   // 시도
    private String city;    // 면읍구군시
    private String detailAddress; // 세부주소

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INFLUENCER_ID")
    private Influencer influencer;  // 인플루언서 참조
}
