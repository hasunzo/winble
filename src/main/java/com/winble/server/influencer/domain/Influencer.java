package com.winble.server.influencer.domain;

import com.winble.server.influencer.domain.enumeration.SignUpType;
import com.winble.server.influencer.domain.enumeration.InfluencerStatus;
import com.winble.server.influencer.domain.enumeration.Role;
import com.winble.server.influencer.domain.profile.BasicProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Influencer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INFLUENCER_ID")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String loginId;

    private String password;
    private String maketingConsent;

    @Embedded
    BasicProfile basicProfile;

    @OneToMany (mappedBy = "influencer", orphanRemoval = true, cascade = CascadeType.PERSIST)  // CascadeType.PERSIST (영속성 전이) : 부모 엔티티를 저장할 때 자식 엔티티도 함께 저장
    private List<Address> address;      // orphanRemoval = true (고아 객체) : 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제하는 기능

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private InfluencerStatus status;

    @Lob
    private String memo;
}
