package com.winble.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus httpStatus;
    private Integer errorCode;
    private String message;

    static ErrorResponse create(BizException e) {
        return new ErrorResponse(e.getHttpStatus(), e.getErrorCode(), e.getMessage());
    }
}
