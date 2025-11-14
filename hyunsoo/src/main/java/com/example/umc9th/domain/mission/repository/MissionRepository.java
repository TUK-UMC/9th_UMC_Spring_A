package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.dto.MissionListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        select new com.example.umc9th.domain.mission.dto.MissionListItem(
            m.id,
            s.name,
            m.condition,
            m.point,
            m.deadline
        )
        from Mission m
          join m.store s
        where s.location.id = :locationId
          and m.deadline >= :today
        order by m.createdAt desc
    """)
    Page<MissionListItem> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("today") LocalDate today,
            Pageable pageable
    );
}
