package com.winble.server.adapter.controller;

import com.winble.server.application.member.MemberService;
import com.winble.server.application.response.ResponseService;
import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.repository.MemberRepository;
import com.winble.server.domain.model.response.ListResult;
import com.winble.server.domain.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class MemberController {
    private final MemberService memberService;
    private final ResponseService responseService;      // 결과 처리 Service

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(value = "/member")
    public ListResult<Member> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(memberService.findAllMember());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "memberId로 회원을 조회한다.")
    @GetMapping(value = "/member/{memberId}")
    public SingleResult<Member> findMemberById(@ApiParam(value = "회원아이디", required = true) @PathVariable long memberId) {
        // 결과데이터가 단일건인경우 getBasicResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(memberService.findMemberById(memberId));
    }

    @ApiOperation(value = "회원 등록", notes = "회원을 등록한다.")
    @PostMapping(value = "/member")
    public SingleResult<Member> save(@ApiParam(value = "회원이메일", required = true) @RequestParam String email,
                                     @ApiParam(value = "회원닉네임", required = true) @RequestParam String nickname) {
        return responseService.getSingleResult(memberService.save(email, nickname));
    }
}
