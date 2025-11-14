package com.umc.study.api.mission.repository;

import com.umc.study.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    
    @Query("SELECT mm FROM MemberMission mm " +
           "JOIN FETCH mm.mission m " +
           "JOIN FETCH m.store s " +
           "WHERE mm.member.id = :memberId AND mm.isComplete = :isComplete " +
           "ORDER BY mm.createdAt DESC")
    Page<MemberMission> findByMemberIdAndIsComplete(@Param("memberId") Long memberId, 
                                                   @Param("isComplete") Boolean isComplete, 
                                                   Pageable pageable);
}