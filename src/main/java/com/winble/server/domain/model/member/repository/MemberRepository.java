package com.winble.server.domain.model.member.repository;

import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.entity.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 자사서비스 회원 로그인 아이디(이메일)로 찾기
    Optional<Member> findByMemberLoginId(String email);
    // 소셜 provider와 로그인 아이디(소셜에서 제공한 식별번호)로 찾기
    Optional<Member> findByMemberLoginIdAndSocialType(String memberLoginId, SocialType socialType);
}
