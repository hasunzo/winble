package com.winble.server.common.exception.authentication.handler;

import com.winble.server.common.exception.BizException;
import com.winble.server.common.exception.influencer.InfluencerAuthenticationErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
온전한 jwt 토큰이 전달되지 않은 경우 토큰 인증 처리 자체가 불가능하기 때문에
토큰 검증 단에서 프로세스가 끝나버리게 된다.
SpringSecurity 에서 제공하는 AuthenticationEntryPoint 를 상속받아 재정의 한다.
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        throw new BizException(InfluencerAuthenticationErrorCode.NOT_CERTIFIED);
    }
}