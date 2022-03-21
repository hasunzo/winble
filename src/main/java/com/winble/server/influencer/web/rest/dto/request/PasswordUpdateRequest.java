package com.winble.server.influencer.web.rest.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class PasswordUpdateRequest {
    @NotBlank(message = "현재 비밀번호를을 입력해주세요.")
    @ApiModelProperty(value = "현재 비밀번호", required = true)
    private String currentPassword;

    @NotBlank(message = "변경할 새 비밀번호를 입력해주세요.")
    @ApiModelProperty(value = "새 비밀번호", required = true)
    private String newPassword;
}
