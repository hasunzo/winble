package com.winble.server.influencer.web.rest;

import com.winble.server.influencer.service.InfluencerService;
import com.winble.server.influencer.web.rest.dto.request.AddressAddRequest;
import com.winble.server.influencer.web.rest.dto.request.AddressUpdateRequest;
import com.winble.server.influencer.web.rest.dto.request.InfluencerUpdateRequest;
import com.winble.server.influencer.web.rest.dto.response.*;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"2. Influencer"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class InfluencerResource {
    private final InfluencerService influencerService;

    // 토큰을 통해 전체 회원을 조회한다.
    // 토큰에 저장된 권한이 ADMIN 이어야 리소스 제공.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(value = "/influencers")
    public ResponseEntity<List<InfluencerAllResponse>> findAllUser(Authentication authentication) {
        List<InfluencerAllResponse> influencerAllResponseList = influencerService.findAllInfluencer()
                .stream()
                .map(InfluencerAllResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(influencerAllResponseList);
    }

    // 토큰에 저장된 회원아이디로 회원 정보를 반환한다.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "토큰값으로 회원을 조회한다.")
    @GetMapping(value = "/influencer")
    public ResponseEntity<InfluencerResponse> findInfluencerById(Authentication authentication) {
        InfluencerResponse influencer = new InfluencerResponse(
                influencerService.findInfluencerByLoginId(authentication.getName()));

        return ResponseEntity.ok(influencer);
    }

    // 인플루언서 기본 정보를 변경한다.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 기본정보 변경", notes = "인플루언서의 기본 정보를 변경합니다.")
    @PatchMapping(value = "/influencer")
    public ResponseEntity<InfluencerUpdateResponse> updateInfluencer(Authentication authentication,
                                                                     @Valid @RequestBody InfluencerUpdateRequest influencerUpdateRequest) {
        InfluencerUpdateResponse influencer = new InfluencerUpdateResponse(
                influencerService.updateInfluencer(authentication.getName(), influencerUpdateRequest)
        );

        return ResponseEntity.ok(influencer);
    }

    // 인플루언서 전체 주소 리스트를 조회한다.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "인플루언서 전체 주소 조회", notes = "인플루언서의 전체 주소 리스트를 조회합니다.")
    @PatchMapping(value = "/influencer/addresses")
    public ResponseEntity<List<AddressResponse>> findAllAddressByInfluencer(Authentication authentication) {
        List<AddressResponse> addressList = influencerService.findAllAddressByInfluencer(authentication.getName())
                .stream()
                .map(AddressResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(addressList);
    }

    // 인플루언서 주소를 추가한다.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "인플루언서 주소 추가", notes = "인플루언서의 주소를 추가합니다.")
    @PostMapping(value = "/influencer/address")
    public ResponseEntity<AddressResponse> addAddress(Authentication authentication,
                                                      @Valid @RequestBody AddressAddRequest addressAddRequest) {
        AddressResponse address = new AddressResponse(
                influencerService.addAddress(authentication.getName(), addressAddRequest)
        );

        return ResponseEntity.ok(address);
    }

    // 인플루언서 주소를 변경한다.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "인플루언서 주소 변경", notes = "인플루언서의 주소를 추가합니다.")
    @PatchMapping(value = "/influencer/address")
    public ResponseEntity<AddressResponse> updateAddress(Authentication authentication,
                                                         @Valid @RequestBody AddressUpdateRequest addressUpdateRequest) {
        AddressResponse address = new AddressResponse(
                influencerService.updateAddress(authentication.getName(), addressUpdateRequest)
        );

        return ResponseEntity.ok(address);
    }

    // 인플루언서 주소를 삭제한다.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "인플루언서 주소 삭제", notes = "인플루언서의 주소를 삭제합니다.")
    @DeleteMapping(value = "/influencer/address/{addressId}")
    public ResponseEntity<Void> deleteAddress(Authentication authentication,
                                              @PathVariable Long addressId) {
        influencerService.deleteAddress(authentication.getName(), addressId);
        return ResponseEntity.noContent().build();
    }
}