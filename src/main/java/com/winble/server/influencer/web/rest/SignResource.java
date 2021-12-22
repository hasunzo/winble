package com.winble.server.influencer.web.rest;

import com.winble.server.influencer.service.SignService;
import com.winble.server.influencer.web.rest.dto.request.InfluencerJoinRequest;
import com.winble.server.influencer.web.rest.dto.request.InfluencerLoginRequest;
import com.winble.server.influencer.web.rest.dto.request.InfluencerSocialLoginRequest;
import com.winble.server.common.response.service.ResponseService;
import com.winble.server.common.response.CommonResult;
import com.winble.server.common.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * 회원 등록, 로그인 처리하는 컨트롤러
 *
 */
@Slf4j
@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignResource {

    private final ResponseService responseService;    // 결과 처리 Service
    private final SignService signService;


    // 자사 서비스 로그인
    // 로그인 후 토큰이 발급된다.
    @ApiOperation(value = "로그인", notes = "자사 서비스 로그인을 한다.")
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@Valid @RequestBody InfluencerLoginRequest influencerLoginRequest) {
        return ResponseEntity.ok(signService.login(influencerLoginRequest));
    }

    // 자사 서비스 회원가입
    // 자사 서비스 회원가입시 필요한 필수 정보는 이메일, 비밀번호다.
    @ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signUp")
    public CommonResult signUp(@Valid @RequestBody InfluencerJoinRequest influencerJoinRequest) {
        signService.signUp(influencerJoinRequest);
        return responseService.getSuccessResult();
    }

    // 소셜 서비스 로그인 혹은 회원가입
    @ApiOperation(value = "소셜 로그인 및 회원가입", notes = "소셜 계정으로 로그인 혹은 회원가입을 한다.")
    @PostMapping(value = "/login/{socialType}")
    public SingleResult socialLoginAndSignUp(@Valid @RequestBody InfluencerSocialLoginRequest influencerSocialLoginRequest) {
        return responseService.getSingleResult(signService.socialLoginAndSignUp(influencerSocialLoginRequest));
    }

}
