package com.winble.server.member.web.rest.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfluencerJoinRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    @ApiModelProperty(value = "회원 아이디(이메일)", required = true)
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @ApiModelProperty(value = "회원 비밀번호", required = true)
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @ApiModelProperty(value = "회원 닉네임", required = true)
    private String nickName;
}
