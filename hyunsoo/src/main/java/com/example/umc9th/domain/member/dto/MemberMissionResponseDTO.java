package com.example.umc9th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
