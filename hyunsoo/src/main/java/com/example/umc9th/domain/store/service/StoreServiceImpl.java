package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.location.entity.Location;
import com.example.umc9th.domain.location.repository.LocationRepository;
import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.StoreResponseDTO;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;
    private final StoreConverter storeConverter;

    @Override
    @Transactional
    public StoreResponseDTO.CreateResultDTO createStore(StoreRequestDTO.CreateDTO request) {
        // 1. 지역 조회
        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("해당 지역을 찾을 수 없습니다."));

        // 2. Store 엔티티 생성
        Store store = Store.builder()
                .name(request.getStoreName())
                .location(location)
                .managerNumber(request.getManagerNumber())
                .build();

        // 3. DB 저장
        Store savedStore = storeRepository.save(store);

        // 4. 응답 DTO 변환
        return storeConverter.toCreateResultDTO(savedStore);
    }
}
