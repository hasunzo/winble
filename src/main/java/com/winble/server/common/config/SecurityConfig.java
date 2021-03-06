package com.winble.server.common.config;

import com.winble.server.common.exception.authentication.handler.CustomAccessDeniedHandler;
import com.winble.server.common.exception.authentication.handler.CustomAuthenticationEntryPoint;
import com.winble.server.influencer.domain.enumeration.Role;
import com.winble.server.common.jwt.JwtAuthenticationFilter;
import com.winble.server.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 스프링 시큐리티 설정
 * @author hasunzo
 */

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;


    // 외부에서 인증관리자를 사용하기 위한 설정
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()  // rest api 이므로 기본설정 사용안함. 기본설정 - 비인증시 로그인폼으로 리다이렉트
                //.cors().configurationSource(corsConfigurationSource())
                .csrf().disable()   // rest api 이므로 csrf 보안이 필요없다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)     // jwt token으로 인증하므로 세션은 생성안함
                .and()
                    .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
                        .antMatchers("/*/login","/*/login/*", "/*/signUp", "/").permitAll()  // 로그인, 회원가입 -> 누구나 접근 가능
                        .antMatchers("/exception/**").permitAll()   // exception -> 누구나 접근 가능
                    .antMatchers("/*/influencers").hasRole(Role.ADMIN.name())   // 모든 유저 정보 -> 관리자만 접근
                    .anyRequest().authenticated()   // 그 외 나머지 요청은 모두 인증된 회원만 접근 가능
                .and()
                    .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣는다.
    }

    @Override   // ignore check swagger resource
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }

/*    // CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

}
