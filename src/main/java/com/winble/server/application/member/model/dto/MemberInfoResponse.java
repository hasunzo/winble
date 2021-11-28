package com.winble.server.application.member.model.dto;

import com.winble.server.domain.model.member.entity.Member;

public interface MemberInfoResponse {
    public String getId();
    Member toMember();
}
