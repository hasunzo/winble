package com.winble.server.member;

import com.winble.server.adapter.controller.member.MemberController;
import com.winble.server.domain.model.member.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
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
    private MemberController memberController;

    private ResultActions actions;

    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();

        actions = this.mockMvc.perform(post("/v1/signup")
                .param("email", "testuser@naver.com")
                .param("nickName", "테스트")
                .param("password", "1234"));
    }

    // 전체회원 조회 테스트
    @Test
    public void 전체회원을_조회한다() {
        List<Member> members = memberController.findAllUser().getList();
        assertThat(1, is(members.size()));
    }

    // 존재하지 않는 회원 Exception 처리 테스트
    @Test
    public void 존재하지_않는_회원을_조회한다() throws Exception {

        ResultActions actions = this.mockMvc.perform(get("/v1/member/23324"));

        actions.andDo(print())
                .andExpect(jsonPath("$.code").value(-1000))
                .andExpect(jsonPath("$.msg").value("존재하지 않는 회원입니다."));
    }

    // 회원 등록 테스트
    @Test
    public void 회원을_등록한다() throws Exception {
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("성공하였습니다."));
    }



}
