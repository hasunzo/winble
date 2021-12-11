package com.winble.server.influencer.web.rest;

import com.google.gson.Gson;
import com.winble.server.dummy.influencer.InfluencerDummy;
import com.winble.server.influencer.repository.InfluencerRepository;
import com.winble.server.influencer.web.rest.dto.request.InfluencerJoinRequest;
import com.winble.server.influencer.web.rest.dto.request.InfluencerLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SignResourceTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private InfluencerRepository influencerRepository;

    private final InfluencerDummy influencerDummy = InfluencerDummy.getInstance();

    // 테스트 진행 전 회원을 등록한다.
    @BeforeEach
    public void setUp() throws Exception {
        // 테스트 한글 깨짐 방지를 위한 UTF-8 설정
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();

        InfluencerJoinRequest request = InfluencerJoinRequest.builder()
                .loginId(influencerDummy.getLoginId())
                .nickName(influencerDummy.getBasicProfile().getNickName())
                .password(influencerDummy.getPassword())
                .build();

        // 직접적으로 repository.save를 통해 회원을 등록하면 패스워드 encode 문제발생
        // api를 통해 회원을 등록한다.
        mockMvc.perform(post("/v1/signUp")
                .content(gson.toJson(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // 자사 로그인 테스트
    @Test
    @DisplayName("회원의 로그인을 테스트합니다.")
    public void loginTest() throws Exception {
        // given
        InfluencerLoginRequest request = InfluencerLoginRequest.builder()
                .loginId(influencerDummy.getLoginId())
                .password(influencerDummy.getPassword())
                .build();

        mockMvc.perform(post("/v1/login")
                .content(gson.toJson(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))   // 응답 성공 유무
                .andExpect(jsonPath("$.code").value(0)) // 응답 코드 번호
                .andExpect(jsonPath("$.msg").exists())      // 응답 메시지
                .andExpect(jsonPath("$.data").exists());    // 토큰
    }

    // 아이디 혹은 비밀번호 예외 테스트
    @Test
    @DisplayName("올바르지 않은 비밀번호로 로그인시 실패합니다.")
    public void loginFailedTest() throws Exception {
        InfluencerLoginRequest influencerLoginRequest = InfluencerLoginRequest.builder()
                .loginId(influencerDummy.getLoginId())
                .password("falsePassword")
                .build();

        mockMvc.perform(post("/v1/login")
                .content(gson.toJson(influencerLoginRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(-1001))
                .andExpect(jsonPath("$.msg").exists())
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    // 자사 회원가입 테스트
    @Test
    @DisplayName("회원가입을 테스트합니다.")
    public void signUpForMembershipTest() throws Exception {
        InfluencerJoinRequest influencerJoinRequest = InfluencerJoinRequest.builder()
                .loginId("test02@test.com")
                .nickName("nickName")
                .password("password")
                .build();

        mockMvc.perform(post("/v1/signUp")
                .content(gson.toJson(influencerJoinRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").exists());
    }

}
