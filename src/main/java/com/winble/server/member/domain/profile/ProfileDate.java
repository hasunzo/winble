package com.winble.server.member.domain.profile;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
@Getter
public class ProfileDate {
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDt;   // 회원가입일시

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDt; // 회원정보변경일시
}
