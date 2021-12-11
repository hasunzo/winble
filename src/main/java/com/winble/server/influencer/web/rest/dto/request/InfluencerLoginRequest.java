package com.winble.server.influencer.web.rest.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class InfluencerLoginRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    @ApiModelProperty(value = "회원 아이디(이메일)", required = true)
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @ApiModelProperty(value = "회원 비밀번호", required = true)
    private String password;

    @Builder
    public InfluencerLoginRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

}
