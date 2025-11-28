package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.store.dto.StoreResponseDTO;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {

    public StoreResponseDTO.CreateResultDTO toCreateResultDTO(Store store) {
        return StoreResponseDTO.CreateResultDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .locationName(store.getLocation().getName())
                .managerNumber(store.getManagerNumber())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
