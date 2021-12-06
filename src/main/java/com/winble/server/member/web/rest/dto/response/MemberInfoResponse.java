package com.winble.server.member.web.rest.dto.response;

import com.winble.server.member.domain.Member;

public interface MemberInfoResponse {
    public String getId();
    Member toMember();
}
