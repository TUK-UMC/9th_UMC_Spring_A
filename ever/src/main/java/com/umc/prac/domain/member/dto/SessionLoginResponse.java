package com.umc.prac.domain.member.dto;

public record SessionLoginResponse(
        Long memberId,
        String name,
        String email
) {
}


