package com.winble.server.influencer.web.rest.dto.request;

import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.profile.Address;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class AddressAddRequest {
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

    public Address toAddress(Influencer influencer) {
        return Address.builder()
                .title(title)
                .recipient(recipient)
                .zipCode(zipCode)
                .addressFirst(addressFirst)
                .addressLast(addressLast)
                .influencer(influencer)
                .build();
    }
}
