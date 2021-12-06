package com.winble.server.member.web.rest;

import com.winble.server.member.service.SignService;
import com.winble.server.member.service.SocialService;
import com.winble.server.response.service.ResponseService;
import com.winble.server.response.domain.CommonResult;
import com.winble.server.response.domain.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public SingleResult<String> login(@ApiParam(value = "회원 ID : 이메일", required = true) @RequestParam String memberLoginId,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {
        return responseService.getSingleResult(signService.login(memberLoginId, password));
    }

    // 자사 서비스 회원가입
    // 자사 서비스 회원가입시 필요한 필수 정보는 이메일, 비밀번호, 닉네임이다.
    @ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/join")
    public CommonResult join(@ApiParam(value = "회원 이메일", required = true) @RequestParam String memberLoginId,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                               @ApiParam(value = "닉네임", required = true) @RequestParam String nickName) {
        signService.join(memberLoginId, password, nickName);
        return responseService.getSuccessResult();
    }

    // 소셜 서비스 로그인
    @ApiOperation(value = "소셜 로그인", notes = "소셜 계정으로 로그인을 한다.")
    @PostMapping(value = "/login/{socialType}")
    public SingleResult loginBySocial(@ApiParam(value = "서비스 제공자", required = true) @PathVariable String socialType,
                                      @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {
        return responseService.getSingleResult(signService.loginBySocial(accessToken, socialType));
    }

    // 소셜 서비스 회원가입
    @ApiOperation(value = "소셜 회원가입", notes = "소셜 계정으로 회원가입을 한다.")
    @PostMapping(value = "/join/{socialType}")
    public CommonResult joinBySocial(@ApiParam(value = "서비스 제공자", required = true) @PathVariable String socialType,
                                       @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {
        signService.joinBySocial(accessToken, socialType);
        return responseService.getSuccessResult();
    }

}
