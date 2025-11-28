package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDTO {
        private Long storeId;     // 가게 ID
        private String content;   // 리뷰 내용
        private Float rate;       // 평점 (0.0 ~ 5.0)
    }
}
