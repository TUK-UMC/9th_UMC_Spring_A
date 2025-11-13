package com.example.umc9th.domain.review.dto;

import java.time.Instant;

public record ReviewDto(
        Long reviewId,
        String storeName,
        Double star,
        String content,
        Instant createdAt
) {}
