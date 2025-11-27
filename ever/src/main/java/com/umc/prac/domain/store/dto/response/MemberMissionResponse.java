package com.umc.prac.domain.store.dto.response;

public record MemberMissionResponse(
        Long memberMissionId,
        Long memberId,
        Long missionId,
        Boolean isComplete
) {
}

