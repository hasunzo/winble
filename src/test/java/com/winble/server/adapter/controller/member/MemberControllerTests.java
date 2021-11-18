package com.winble.server.adapter.controller.member;

import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.entity.Role;
import com.winble.server.domain.model.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
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
                .apply(springSecurity())    // MockMvcBuilders를 사용할땐 springSecurity()를 적용해주어야 한다.
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
    @DisplayName("관리자 권한으로 전체 회원을 조회합니다.")
    @WithMockUser(username = "mockUser", roles = {"ADMIN"})
    public void findAllMembersWithADMINRolesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/members"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // 해당 리소스에 접근하지 못하는 권한일 때 exception 테스트
    @Test
    @DisplayName("허용되지 않은 권한으로 전체 회원 조회 리소스 요청시 거부됩니다.")
    @WithMockUser(username = "mockUser", roles = {"INFLUENCER"})
    public void findAllMembersWithDisallowedRolesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/members"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exception/accessdenied"));
    }

    // 회원 정보 조회 테스트
    @Test
    @DisplayName("로그인한 회원의 정보를 조회합니다.")
    @WithMockUser(username = "test@test.com", roles = {"INFLUENCER"})
    public void findMember() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/member"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberEmail").value("test@test.com"))
                .andExpect(jsonPath("$.data.nickName").value("홍길동"));
    }

}
