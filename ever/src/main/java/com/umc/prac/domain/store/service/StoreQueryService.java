package com.umc.prac.domain.store.service;

import com.umc.prac.domain.store.dto.response.MissionResponse;
import com.umc.prac.domain.mission.entity.Mission;
import com.umc.prac.domain.mission.repository.MissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StoreQueryService {

    private final MissionRepository missionRepository;

    public StoreQueryService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    // 페이징으로 특정 가게의 미션 목록 조회
    public Page<MissionResponse> getMissionsByStoreId(Long storeId, Pageable pageable) {
        Page<Mission> missions = missionRepository.findByStoreStoreId(storeId, pageable);
        return missions.map(m -> new MissionResponse(m.getMissionId(), m.getStore().getStoreId(), m.getConditional(), m.getPoint(), m.getDeadline(), m.getCreatedAt()));
    }
}
