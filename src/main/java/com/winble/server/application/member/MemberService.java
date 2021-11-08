package com.winble.server.application.member;

import com.winble.server.adapter.advice.exception.CMemberNotFoundException;
import com.winble.server.application.response.ResponseService;
import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.repository.MemberRepository;
import com.winble.server.domain.model.response.ListResult;
import com.winble.server.domain.model.response.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 전체 멤버를 조회하는 메소드
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    // 특정 한 멤버를 조회하는 메소드
    public Member findMemberById(long memberId) {
        // Exception 발생시 CMemberNotFoundException 생성
        return memberRepository.findById(memberId).orElseThrow(CMemberNotFoundException::new);
    }

    // 회원을 등록하는 메소드
    public Member save(String email, String nickname) {
        Member member = Member.builder()
                .memberEmail(email)
                .memberNickName(nickname)
                .build();
        return memberRepository.save(member);
    }
}
