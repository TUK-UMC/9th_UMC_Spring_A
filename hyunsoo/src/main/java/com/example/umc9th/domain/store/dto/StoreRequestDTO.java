package com.example.umc9th.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDTO {
        private String storeName;      // 가게 이름
        private Long locationId;       // 지역 ID
        private String managerNumber;  // 매니저 번호
    }
}
