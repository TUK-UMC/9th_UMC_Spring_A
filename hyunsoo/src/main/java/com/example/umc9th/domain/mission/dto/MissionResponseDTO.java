package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDTO {
        private List<MissionDTO> missions;
        private Integer currentPage;
        private Integer totalPages;
        private Long totalElements;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDTO {
        private Long missionId;
        private String storeName;
        private String condition;
        private Integer point;
        private LocalDate deadline;
    }
}
