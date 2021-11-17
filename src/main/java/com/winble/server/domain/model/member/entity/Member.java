package com.winble.server.domain.model.member.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {    // SpringSecurity의 보안을 적용하기 위해 UserDetails 상속
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;        // 회원식별번호

    @NotNull
    @Column(unique = true)
    private String memberEmail;     // 회원이메일

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Column(length = 100)
    private String password;        // 회원비밀번호

    @Column(length = 30)
    private String memberName;      // 회원이름

    @NotNull
    private String nickName;        // 회원닉네임

    @Column(length = 20, unique = true)
    private String phoneNumber;     // 휴대폰번호

    @Enumerated(EnumType.STRING)
    private Role role;              // 회원 권한

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDt;   // 회원가입일시

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDt; // 회원정보변경일시

    private String smsReceptionConsent;    // SMS수신 동의여부

    private String mailReceptionConsent;   // 메일수신 동의여부

    private String termsOfServiceConsent;   // 서비스이용약관 동의여부

    private String personalInfoCollectionAndUsageConsent;     // 개인정보 동의여부

    @Enumerated(EnumType.STRING)
    private MemberStatus memberState;     // 회원상태

    @Lob
    private String memberMemo;      // 회원메모

}
