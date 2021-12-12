package com.winble.server.exception;

import com.winble.server.exception.authentication.CAuthenticationEntryPointException;
import com.winble.server.exception.influencer.CEmailLoginFailedException;
import com.winble.server.exception.influencer.CInfluencerExistException;
import com.winble.server.exception.influencer.CInfluencerNotFoundException;
import com.winble.server.exception.social.CCommunicationException;
import com.winble.server.common.response.service.ResponseService;
import com.winble.server.common.response.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

// Exception 발생시 ExceptionAdvice 에서 처리
@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    // 그 외 Exception 발생시 처리하는 defalutException
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        log.info(String.valueOf(e.getStackTrace()));
        log.info(e.toString());
        log.info(String.valueOf(e.getCause()));
        return responseService.getFailResult(-9999, "알 수 없는 오류가 발생하였습니다.");
    }

    // 해당 회원을 찾을 수 없을 때
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CInfluencerNotFoundException e) {
        return responseService.getFailResult(-1000, "존재하지 않는 회원입니다.");
    }

    // 아이디 혹은 비밀번호가 일치하지 않을 때
    @ExceptionHandler(CEmailLoginFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailLoginFailed(HttpServletRequest request, CEmailLoginFailedException e) {
        return responseService.getFailResult(-1001, "계정이 존재하지 않거나 이메일 또는 비밀번호가 정확하지 않습니다.");
    }

    // jwt 토큰 없을 때
    // 토큰이 형식에 맞지 않거나 만료되었을 때
    @ExceptionHandler(CAuthenticationEntryPointException.class)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return responseService.getFailResult(-1002, "해당 리소스에 접근하기 위한 권한이 없습니다.");
    }

    // jwt 토큰으로 api를 호출하였으나 해당 리소스에 접근 권한이 없을 때
    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult(-1003, "보유한 권한으로 접근할 수 없는 리소스 입니다.");
    }

    // 통신 중 오류 상황이 발생했을 때
    @ExceptionHandler(CCommunicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult communicationException(HttpServletRequest request, CCommunicationException e) {
        return responseService.getFailResult(-1004, "통신 중 오류가 발생했습니다.");
    }

    // 이미 가입한 회원일 때
    public CommonResult memberExistException(HttpServletRequest request, CInfluencerExistException e) {
        return responseService.getFailResult(-1005, "이미 가입한 회원입니다.");
    }
}
