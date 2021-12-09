package com.winble.server.member.domain;

import com.winble.server.member.domain.enumeration.SignUpType;
import com.winble.server.member.domain.enumeration.MemberStatus;
import com.winble.server.member.domain.enumeration.Role;
import com.winble.server.member.domain.profile.BasicProfile;
import com.winble.server.member.domain.profile.ProfileDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Influencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INFLUENCER_ID")
    private Long id;

    private String loginId;
    private String password;
    private String maketingConsent;

    @Embedded
    BasicProfile basicProfile;

    @Embedded
    ProfileDate profileDate;

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Lob
    private String memo;
}
