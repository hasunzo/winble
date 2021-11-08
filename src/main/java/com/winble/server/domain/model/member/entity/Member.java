package com.winble.server.domain.model.member.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;        // 회원식별번호

    @NotNull
    @Column(unique = true)
    private String memberEmail;     // 회원이메일

    @Column(length = 30)
    private String memberName;      // 회원이름

    @NotNull
    @Column(length = 30, unique = true)
    private String memberNickName;  // 회원닉네임

    @Column(length = 20, unique = true)
    private String phoneNumber;     // 휴대폰번호

    @Enumerated(EnumType.STRING)
    private Role role;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDt;   // 회원가입일시

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDt; // 회원정보변경일시

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;     // 생년월일

    private String smsReceptionConsent;    // SMS수신 동의여부

    private String mailReceptionConsent;   // 메일수신 동의여부

    private String termsOfServiceConsent;   // 서비스이용약관 동의여부

    private String personalInfoCollectionAndUsageConsent;     // 개인정보 동의여부

    @Enumerated(EnumType.STRING)
    private MemberStatus memberState;     // 회원상태

    @Lob
    private String memberMemo;      // 회원메모

}
