package com.umc.prac.domain.mission.repository;

import com.umc.prac.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}

