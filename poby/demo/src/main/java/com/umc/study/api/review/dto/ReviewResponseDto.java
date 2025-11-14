package com.umc.study.api.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private String content;
    private Float rating;
    private String memberName;
    private String storeName;
    private LocalDateTime createdAt;
}