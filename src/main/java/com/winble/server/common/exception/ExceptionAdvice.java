package com.winble.server.common.exception;

import com.winble.server.common.response.service.ResponseService;
import com.winble.server.common.response.CommonResult;
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
        log.info(String.valueOf(e.getStackTrace()));
        log.info(e.toString());
        log.info(String.valueOf(e.getCause()));
        return responseService.getFailResult(-9999, "알 수 없는 오류가 발생하였습니다.");
    }
}
