package com.winble.server.adapter.controller.member;

import com.winble.server.adapter.config.security.JwtTokenProvider;
import com.winble.server.application.member.SignService;
import com.winble.server.application.response.ResponseService;
import com.winble.server.domain.model.member.repository.MemberRepository;
import com.winble.server.domain.model.response.CommonResult;
import com.winble.server.domain.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 회원 등록, 로그인 처리하는 컨트롤러
 *
 */
@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final ResponseService responseService;    // 결과 처리 Service
    private final SignService signService;


    // 자사 서비스 로그인
    // 로그인 후 토큰이 발급된다.
    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/login")
    public SingleResult<String> login(@ApiParam(value = "회원 이메일", required = true) @RequestParam String memberEmail,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {
        return responseService.getSingleResult(signService.login(memberEmail, password));
    }

    // 자사 서비스 회원가입
    // 자사 서비스 회원가입시 필요한 필수 정보는 이메일, 비밀번호, 닉네임이다.
    @ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/join")
    public CommonResult join(@ApiParam(value = "회원 이메일", required = true) @RequestParam String memberEmail,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                               @ApiParam(value = "닉네임", required = true) @RequestParam String nickName) {
        signService.join(memberEmail, password, nickName);
        return responseService.getSuccessResult();
    }

}
