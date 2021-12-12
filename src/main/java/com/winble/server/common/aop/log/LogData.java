package com.winble.server.common.aop.log;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogData {
    private String timestamp;
    private String hostIp;
    private String clientIp;
    private String clientUrl;
    private String callFunction;
    private String parameter;
}
