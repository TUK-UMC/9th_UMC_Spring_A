package com.umc.prac.domain.store.dto.request;

import jakarta.validation.constraints.NotNull;

public record MissionChallengeRequest(
        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId
) {
}

