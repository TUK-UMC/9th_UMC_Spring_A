package com.umc.prac.domain.store.dto.response;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId,
        Long memberId,
        Long storeId,
        Double star,
        String content,
        LocalDateTime createdAt
) {
}

