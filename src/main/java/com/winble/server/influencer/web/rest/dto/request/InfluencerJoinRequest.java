package com.winble.server.influencer.web.rest.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
public class InfluencerJoinRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    @ApiModelProperty(value = "회원 아이디(이메일)", required = true)
    private final String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @ApiModelProperty(value = "회원 비밀번호", required = true)
    private final String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @ApiModelProperty(value = "회원 닉네임", required = true)
    private final String nickName;
}
