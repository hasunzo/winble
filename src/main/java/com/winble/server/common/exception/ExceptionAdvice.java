package com.winble.server.common.exception;

import com.winble.server.common.response.service.ResponseService;
import com.winble.server.common.response.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

// Exception 발생시 ExceptionAdvice 에서 처리
@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BizException.class)
    public ResponseEntity<ErrorResponse> catchBizException(BizException exception) {
        return new ResponseEntity<>(ErrorResponse.create(exception), exception.getHttpStatus());
    }
}