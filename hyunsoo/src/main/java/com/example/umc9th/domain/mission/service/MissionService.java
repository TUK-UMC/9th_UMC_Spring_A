package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MissionConverter missionConverter;

    // 특정 가게의 미션 목록 조회 (페이징)
    public MissionResponseDTO.MissionListDTO getStoreMissions(Long storeId, Integer page) {
        // 1. 가게 존재 여부 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("해당 가게를 찾을 수 없습니다."));

        // 2. 페이징 설정 (page는 1-based, PageRequest는 0-based)
        Pageable pageable = PageRequest.of(page - 1, 10);

        // 3. 미션 목록 조회
        Page<Mission> missionPage = missionRepository.findByStoreIdOrderByCreatedAtDesc(storeId, pageable);

        // 4. DTO 변환
        return missionConverter.toMissionListDTO(missionPage);
    }
}
