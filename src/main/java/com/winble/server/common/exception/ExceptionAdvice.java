package com.winble.server.common.exception;

import com.winble.server.common.exception.authentication.CAuthenticationEntryPointException;
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
}
