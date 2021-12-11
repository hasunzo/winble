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

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private InfluencerStatus status;

    @Lob
    private String memo;
}
