package com.umc.prac.domain.member.repository;

import com.umc.prac.domain.member.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMember_MemberIdAndIsComplete(Long memberId, Boolean isComplete, Pageable pageable);
}


