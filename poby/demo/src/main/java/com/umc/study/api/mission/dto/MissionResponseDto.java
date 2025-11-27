package com.umc.study.api.mission.dto;

import com.umc.study.domain.mission.entity.Mission;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionResponseDto {
    private Long missionId;
    private String conditional;
    private Integer point;
    private LocalDate deadline;
    private String storeName;

    public static MissionResponseDto from(Mission mission) {
        return MissionResponseDto.builder()
                .missionId(mission.getId())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .storeName(mission.getStore().getName())
                .build();
    }
}