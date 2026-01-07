package com.example.umc9th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeResultDTO {
        private Long memberMissionId;
        private String memberName;
        private String storeName;
        private String missionCondition;
        private LocalDate missionDeadline;
        private Integer point;
        private Boolean isComplete;
        private LocalDateTime challengedAt;
    }

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
        private Long memberMissionId;
        private String storeName;
        private String missionCondition;
        private Integer point;
        private LocalDate deadline;
        private LocalDateTime challengedAt;
    }
}
