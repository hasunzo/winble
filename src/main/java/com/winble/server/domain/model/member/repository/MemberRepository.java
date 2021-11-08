package com.winble.server.domain.model.member.repository;

import com.winble.server.domain.model.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
