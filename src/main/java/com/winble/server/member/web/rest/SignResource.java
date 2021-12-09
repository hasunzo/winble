package com.winble.server.member.web.rest;

import com.winble.server.member.service.SignService;
import com.winble.server.member.service.SocialService;
import com.winble.server.member.web.rest.dto.request.InfluencerJoinRequest;
import com.winble.server.member.web.rest.dto.request.InfluencerLoginRequest;
import com.winble.server.member.web.rest.dto.request.InfluencerSocialLoginRequest;
import com.winble.server.response.service.ResponseService;
import com.winble.server.response.domain.CommonResult;
import com.winble.server.response.domain.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * 회원 등록, 로그인 처리하는 컨트롤러
 *
 */
@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignResource {

    private final ResponseService responseService;    // 결과 처리 Service
    private final SignService signService;
    private final SocialService socialService;      // 소셜 로그인 Service


    // 자사 서비스 로그인
    // 로그인 후 토큰이 발급된다.
    @ApiOperation(value = "로그인", notes = "자사 서비스 로그인을 한다.")
    @PostMapping(value = "/login")
    public SingleResult<String> login(@Valid InfluencerLoginRequest influencerLoginRequest) {
        return responseService.getSingleResult(signService.login(influencerLoginRequest));
    }

    // 자사 서비스 회원가입
    // 자사 서비스 회원가입시 필요한 필수 정보는 이메일, 비밀번호다.
    @ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signUp")
    public CommonResult signUp(@Valid InfluencerJoinRequest influencerJoinRequest) {
        signService.signUp(influencerJoinRequest);
        return responseService.getSuccessResult();
    }

/*
    // 소셜 서비스 로그인
    @ApiOperation(value = "소셜 로그인", notes = "소셜 계정으로 로그인을 한다.")
    @PostMapping(value = "/login/{socialType}")
    public SingleResult loginBySocial(@ApiParam(value = "서비스 제공자", required = true) @PathVariable String socialType,
                                      @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {
        return responseService.getSingleResult(signService.loginBySocial(accessToken, socialType));
    }

    // 소셜 서비스 회원가입
    @ApiOperation(value = "소셜 회원가입", notes = "소셜 계정으로 회원가입을 한다.")
    @PostMapping(value = "/signUp/{socialType}")
    public CommonResult signUpBySocial(@ApiParam(value = "서비스 제공자", required = true) @PathVariable String socialType,
                                       @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {
        signService.signUpBySocial(accessToken, socialType);
        return responseService.getSuccessResult();
    }
*/

    // 소셜 서비스 로그인 혹은 회원가입
    @ApiOperation(value = "소셜 로그인 및 회원가입", notes = "소셜 계정으로 로그인 혹은 회원가입을 한다.")
    @PostMapping(value = "/login/{socialType}")
    public SingleResult socialLoginAndSignUp(@Valid InfluencerSocialLoginRequest influencerSocialLoginRequest) {
        return responseService.getSingleResult(signService.socialLoginAndSignUp(influencerSocialLoginRequest));
    }

}
