package com.winble.server.influencer.web.rest.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class AddressUpdateRequest {
    @NonNull
    @ApiModelProperty(value = "주소 번호", required = true)
    private final Long id;

    @NotBlank(message = "배송지명을 입력해주세요.")
    @ApiModelProperty(value = "배송지명", required = true)
    private final String title;

    @NotBlank(message = "수령인을 입력해주세요.")
    @ApiModelProperty(value = "수령인", required = true)
    private final String recipient;

    @NotBlank(message = "우편번호를 입력해주세요.")
    @ApiModelProperty(value = "우편번호", required = true)
    private final String zipCode;

    @NotBlank(message = "상세주소를 입력해주세요.")
    @ApiModelProperty(value = "상세주소", required = true)
    private final String addressFirst;

    @ApiModelProperty(value = "추가 주소", required = true)
    private final String addressLast;
}
