package com.umc.study.api.mission.dto;

import com.umc.study.domain.mission.entity.MemberMission;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class MemberMissionResponseDto {
    private Long memberMissionId;
    private Long missionId;
    private String conditional;
    private Integer point;
    private LocalDate deadline;
    private String storeName;
    private Boolean isComplete;
    private LocalDateTime createdAt;

    public static MemberMissionResponseDto from(MemberMission memberMission) {
        return MemberMissionResponseDto.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .conditional(memberMission.getMission().getConditional())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline())
                .storeName(memberMission.getMission().getStore().getName())
                .isComplete(memberMission.getIsComplete())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}