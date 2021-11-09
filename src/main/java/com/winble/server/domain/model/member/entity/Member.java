package com.winble.server.domain.model.member.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member implements UserDetails {    // SpringSecurity의 보안을 적용하기 위해 UserDetails 상속
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;        // 회원식별번호

    @NotNull
    @Column(unique = true)
    private String memberEmail;     // 회원이메일

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Column(length = 100)
    private String password;

    @Column(length = 30)
    private String memberName;      // 회원이름

    @Column(length = 20, unique = true)
    private String phoneNumber;     // 휴대폰번호

    @Enumerated(EnumType.STRING)
    private Role role;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.memberEmail;
    }

    // 계정이 만료가 안되었는지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠기지 않았는지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 패스워드가 만료 안되었는지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 사용 가능한지지
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
