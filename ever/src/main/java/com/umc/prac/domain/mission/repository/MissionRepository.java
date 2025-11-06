package com.umc.prac.domain.mission.repository;

import com.umc.prac.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("select m from Mission m " +
           "where m.store.location.locationId = :locationId " +
           "and m.deadline >= :today " +
           "and not exists (select 1 from MemberMission mm where mm.mission = m and mm.member.memberId = :memberId)")
    Page<Mission> findAvailableMissions(@Param("locationId") Long locationId,
                                        @Param("memberId") Long memberId,
                                        @Param("today") LocalDate today,
                                        Pageable pageable);
}


