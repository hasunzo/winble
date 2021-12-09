package com.winble.server.member.web.rest;

import com.winble.server.member.domain.Influencer;
import com.winble.server.member.service.InfluencerService;
import com.winble.server.member.web.rest.dto.response.InfluencerAllResponse;
import com.winble.server.member.web.rest.dto.response.InfluencerResponse;
import com.winble.server.response.service.ResponseService;
import com.winble.server.response.domain.ListResult;
import com.winble.server.response.domain.SingleResult;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"2. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class MemberResource {
    private final InfluencerService influencerService;
    private final ResponseService responseService;      // 결과 처리 Service

    // 토큰을 통해 전체 회원을 조회한다.
    // 토큰에 저장된 권한이 ADMIN 이어야 리소스 제공.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(value = "/members")
    public ListResult<InfluencerAllResponse> findAllUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.

        List<InfluencerAllResponse> influencerAllResponseList = influencerService.findAllInfluencer()
                .stream()
                .map(entity -> new InfluencerAllResponse(entity))
                .collect(Collectors.toList());
        return responseService.getListResult(influencerAllResponseList);
    }

    // 토큰에 저장된 회원아이디로 회원 정보를 반환한다.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "토큰값으로 회원을 조회한다.")
    @GetMapping(value = "/member")
    public SingleResult<InfluencerResponse> findMemberByEmail() {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        InfluencerResponse influencer = new InfluencerResponse(
                influencerService.findInfluencerByLoginId(authentication.getName()));

        // 결과데이터가 단일건인경우 getBasicResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(influencer);
    }

    //TODO: 회원 수정, 회원 삭제

}