package com.winble.server.adapter.advice.exception;

import com.winble.server.adapter.advice.exception.member.CEmailLoginFailedException;
import com.winble.server.adapter.advice.exception.member.CMemberNotFoundException;
import com.winble.server.application.response.ResponseService;
import com.winble.server.domain.model.response.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        return responseService.getFailResult(-9999, "알 수 없는 오류가 발생하였습니다.");
    }

    // 해당 회원을 찾을 수 없을 때
    @ExceptionHandler(CMemberNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CMemberNotFoundException e) {
        return responseService.getFailResult(-1000, "존재하지 않는 회원입니다.");
    }

    // 아이디 혹은 비밀번호가 일치하지 않을 때
    @ExceptionHandler(CEmailLoginFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailLoginFailed(HttpServletRequest request, CEmailLoginFailedException e) {
        return responseService.getFailResult(-1001, "계정이 존재하지 않거나 이메일 또는 비밀번호가 정확하지 않습니다.");
    }


}
