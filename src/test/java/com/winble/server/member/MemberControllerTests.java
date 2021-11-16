package com.winble.server.member;

import com.winble.server.adapter.controller.member.MemberController;
import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.entity.Role;
import com.winble.server.domain.model.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class MemberControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setUp() throws Exception {
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

    // 전체 회원 조회 테스트
    @Test
    @WithMockUser(username = "mockUser", roles = {"ADMIN"})
    public void 전체_회원_조회() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/members"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(1));;
    }

    // 회원 정보 조회 테스트
    @Test
    @WithMockUser(username = "test@test.com", roles = {"INFLUENCER"})
    public void 회원정보_조회() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/member"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberEmail").value("test@test.com"))
                .andExpect(jsonPath("$.data.nickName").value("홍길동"));
    }

}
