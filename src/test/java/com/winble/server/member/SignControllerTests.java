package com.winble.server.member;

import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.entity.Role;
import com.winble.server.domain.model.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SignControllerTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    // 테스트 진행 전 회원을 등록한다.
    @BeforeEach
    public void setUp() {
        // 테스트 한글 깨짐 방지를 위한 UTF-8 설정
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();

        memberRepository.save(Member.builder()
                        .memberEmail("test@test.com")
                        .nickName("홍길동")
                        .password(passwordEncoder.encode("1234"))
                        .role(Role.INFLUENCER)
                        .build());
    }

    // 자사 로그인 테스트
    @Test
    public void 로그인_테스트() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("memberEmail", "test@test.com");     // 회원 이메일
        params.add("password", "1234");                 // 회원 비밀번호

        // return SingleResult
        mockMvc.perform(post("/v1/login").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))   // 응답 성공 유무
                .andExpect(jsonPath("$.code").value(0)) // 응답 코드 번호
                .andExpect(jsonPath("$.msg").exists())      // 응답 메시지
                .andExpect(jsonPath("$.data").exists());    // 토큰
    }

    // 아이디 혹은 비밀번호 예외 테스트
    @Test
    public void 로그인_실패() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("memberEmail", "test@test.com");
        params.add("password", "0000");     // 올바르지 않은 비밀번호 입력

        mockMvc.perform(post("/v1/login").params(params))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(-1001))
                .andExpect(jsonPath("$.msg").exists())
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    // 자사 회원가입 테스트
    @Test
    public void 회원가입_테스트() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("memberEmail", "joinTestUser@test.com");
        params.add("password", "1234");
        params.add("nickName", "테스트닉네임");

        // return CommonResult
        mockMvc.perform(post("/v1/join").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").exists());
    }

}
