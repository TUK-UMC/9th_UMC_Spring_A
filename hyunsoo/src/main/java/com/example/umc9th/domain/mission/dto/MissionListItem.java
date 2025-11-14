package com.example.umc9th.domain.mission.dto;

import java.time.LocalDate;

public record MissionListItem(
        Long missionId,
        String storeName,
        String condition,
        Integer point,
        LocalDate deadline
) {}
