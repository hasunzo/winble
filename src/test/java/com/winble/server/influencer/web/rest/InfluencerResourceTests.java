package com.winble.server.influencer.web.rest;

import com.google.gson.Gson;
import com.winble.server.dummy.influencer.InfluencerDummy;
import com.winble.server.influencer.repository.InfluencerRepository;
import com.winble.server.influencer.web.rest.dto.request.InfluencerJoinRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class InfluencerResourceTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    private Gson gson;

    private final InfluencerDummy influencerDummy = InfluencerDummy.getInstance();

    @BeforeEach
    public void setUp() throws Exception {
        // 테스트 한글 깨짐 방지를 위한 UTF-8 설정
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())    // MockMvcBuilders를 사용할땐 springSecurity()를 적용해주어야 한다.
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();

        InfluencerJoinRequest request = InfluencerJoinRequest.builder()
                .loginId(influencerDummy.getLoginId())
                .nickName(influencerDummy.getBasicProfile().getNickName())
                .password(influencerDummy.getPassword())
                .build();

        mockMvc.perform(post("/v1/signUp")
                .content(gson.toJson(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // 전체 회원 조회 테스트
    @Test
    @DisplayName("관리자 권한으로 전체 회원을 조회합니다.")
    @WithMockUser(username = "mockUser", roles = {"ADMIN"})
    public void findAllMembersWithADMINRolesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/influencers"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // 해당 리소스에 접근하지 못하는 권한일 때 exception 테스트
    @Test
    @DisplayName("허용되지 않은 권한으로 전체 회원 조회 리소스 요청시 거부됩니다.")
    @WithMockUser(username = "mockUser")
    public void findAllMembersWithDisallowedRolesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/influencers"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exception/accessdenied"));
    }

    // 회원 정보 조회 테스트
    @Test
    @DisplayName("로그인한 회원의 정보를 조회합니다.")
    @WithMockUser(username = "test01@test.com")
    public void findMember() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/influencer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.loginId").value(influencerDummy.getLoginId()))
                .andExpect(jsonPath("$.data.nickName").value(influencerDummy.getBasicProfile().getNickName()));
    }

}
