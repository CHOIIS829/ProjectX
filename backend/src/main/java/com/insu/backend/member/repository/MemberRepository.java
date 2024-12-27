package com.insu.backend.member.repository;

import com.insu.backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);

    boolean existsByMemberId(String memberId);

    Optional<Member> findByMemberNameAndEmail(String memberName, String email);
}
