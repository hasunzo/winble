package com.winble.server.domain.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * 다중결과
 *
 */
@Getter
@Setter
public class ListResult<T> extends CommonResult {
    private List<T> list;
}
