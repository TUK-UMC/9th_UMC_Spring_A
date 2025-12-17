package com.umc.prac.domain.member.dto;

public record MemberJoinResponse(
        Long memberId,
        String name,
        String email
) {
}


