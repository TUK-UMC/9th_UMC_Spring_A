package com.umc.study.api.review.dto;

import com.umc.study.domain.review.entity.Review;
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
    private String storeName;
    private LocalDateTime createdAt;



    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }
}