package com.winble.server.member.web.rest.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfluencerSocialLoginRequest {

    @NotBlank(message = "토큰을 입력해주세요.")
    @ApiModelProperty(value = "소셜 로그인 토큰", required = true)
    private String accessToken;

    @NotBlank(message = "소셜 타입을 입력해주세요.")
    @ApiModelProperty(value = "소셜 로그인 타입", required = true)
    private String signUpType;
}
