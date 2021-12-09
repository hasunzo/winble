package com.winble.server.member.repository;

import com.winble.server.member.domain.Influencer;
import com.winble.server.member.domain.enumeration.SignUpType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
    // 자사서비스 회원 로그인 아이디(자사: 이메일)로 찾기
    Optional<Influencer> findByLoginId(String loginId);
    // 소셜 provider와 로그인 아이디(소셜에서 제공한 식별번호)로 찾기
    Optional<Influencer> findByLoginIdAndSignUpType(String loginId, String signUpType);
    // 회원이 존재하는 지를 확인
    boolean existsByLoginIdAndSignUpType(String loginId, String signUpType);
}
