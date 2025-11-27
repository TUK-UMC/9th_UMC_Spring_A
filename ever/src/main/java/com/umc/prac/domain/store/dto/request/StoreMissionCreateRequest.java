package com.umc.prac.domain.store.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record StoreMissionCreateRequest(
        @NotNull(message = "마감일은 필수입니다.")
        @FutureOrPresent(message = "마감일은 오늘 또는 미래여야 합니다.")
        LocalDate deadline,

        @NotBlank(message = "미션 조건은 필수입니다.")
        String conditional,

        @NotNull(message = "포인트는 필수입니다.")
        @Positive(message = "포인트는 양수여야 합니다.")
        Integer point
) {
}

