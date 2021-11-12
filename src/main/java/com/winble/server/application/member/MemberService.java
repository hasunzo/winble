package com.winble.server.application.member;

import com.winble.server.adapter.advice.exception.member.CMemberNotFoundException;
import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 전체 멤버 리스트를 반환하는 메소드
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    // 회원 이메일로 회원 정보를 반환하는 메소드
    public Member findMemberByEmail(String memberEmail) {
        // Exception 발생시 CMemberNotFoundException 생성
        return memberRepository.findByMemberEmail(memberEmail).orElseThrow(CMemberNotFoundException::new);
    }
}
