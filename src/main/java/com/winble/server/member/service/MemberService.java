package com.winble.server.member.service;

import com.winble.server.exception.member.CMemberNotFoundException;
import com.winble.server.member.domain.Member;
import com.winble.server.member.repository.MemberRepository;
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
        return memberRepository.findByMemberLoginId(memberEmail).orElseThrow(CMemberNotFoundException::new);
    }
}
