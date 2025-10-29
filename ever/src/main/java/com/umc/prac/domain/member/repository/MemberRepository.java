package com.umc.prac.domain.member.repository;

import com.umc.prac.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select count(r) from Review r where r.member.memberId = :memberId")
    long countReviews(@Param("memberId") Long memberId);
}


