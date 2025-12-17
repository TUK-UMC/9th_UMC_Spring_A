package com.umc.prac.domain.mission.repository;

import com.umc.prac.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 가게의 미션 목록 조회 (페이징)
    Page<Mission> findByStoreStoreId(Long storeId, Pageable pageable);
}
