package com.winble.server.common.exception.authentication.handler;

import com.winble.server.common.exception.BizException;
import com.winble.server.common.exception.influencer.InfluencerAuthenticationErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
jwt 토큰은 정산이지만 해당 토큰의 권한이 리소스에 접근할 수 없는 권한일 때 발생하는 오류.
SpringSecurity 에서 제공하는 AccessDeniedHandler 를 상속받아 재정의 한다.
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        throw new BizException(InfluencerAuthenticationErrorCode.NOT_GRANTED);
    }
}
