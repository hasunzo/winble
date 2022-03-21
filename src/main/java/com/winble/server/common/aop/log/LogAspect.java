package com.winble.server.common.aop.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Slf4j
@Aspect
@Component
public class LogAspect {
    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private ObjectMapper objectMapper = new ObjectMapper();

    private String hostIp = "";
    private String clientIp = "";
    private String clientUrl = "";

    @PostConstruct
    public void init() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        this.hostIp = addr.getHostAddress();
    }

    @Around("execution(* com.winble.server..*Resource.*(..))")
    public Object controllerLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String timeStamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Timestamp(System.currentTimeMillis()));
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        this.clientIp = request.getRemoteAddr();
        this.clientUrl = request.getRequestURI().toString();
        String callFunction = proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName();
        String parameter = objectMapper.writeValueAsString(request.getParameterMap());

        LogData logData = new LogData();
        logData.setTimestamp(timeStamp);
        logData.setHostIp(hostIp);
        logData.setClientIp(clientIp);
        logData.setClientUrl(clientUrl);
        logData.setCallFunction(callFunction);
        logData.setParameter(parameter);

        log.info("{}", objectMapper.writeValueAsString(logData));

        Object object = proceedingJoinPoint.proceed();

        log.info(object.toString());

        return object;
    }

}
