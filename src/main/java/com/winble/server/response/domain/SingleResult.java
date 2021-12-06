package com.winble.server.response.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 단일 결과
 *
 */
@Getter
@Setter
public class SingleResult<T> extends CommonResult{
    private T data;
}
