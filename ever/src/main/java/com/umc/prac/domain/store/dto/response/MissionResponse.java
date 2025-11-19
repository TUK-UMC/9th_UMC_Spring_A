package com.umc.prac.domain.store.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MissionResponse(
        Long missionId,
        Long storeId,
        String conditional,
        Integer point,
        LocalDate deadline,
        LocalDateTime createdAt
) {
}

