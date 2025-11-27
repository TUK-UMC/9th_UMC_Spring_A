package com.umc.prac.domain.member.repository;

import com.umc.prac.domain.member.entity.Member;
import com.umc.prac.domain.member.entity.MemberMission;
import com.umc.prac.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    boolean existsByMemberAndMission(Member member, Mission mission);

    // 특정 회원의 진행중(완료 아님)인 미션 목록 조회
    List<MemberMission> findByMemberAndIsCompleteFalse(Member member);

    // 페이징 조회
    Page<MemberMission> findByMemberAndIsCompleteFalse(Member member, Pageable pageable);
}
