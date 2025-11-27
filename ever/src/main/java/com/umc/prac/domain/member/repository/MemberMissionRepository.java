package com.umc.prac.domain.member.repository;

import com.umc.prac.domain.member.entity.Member;
import com.umc.prac.domain.member.entity.MemberMission;
import com.umc.prac.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    boolean existsByMemberAndMission(Member member, Mission mission);
}

