package com.umc.prac.domain.review.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyReviewDto {
    private Long reviewId;
    private String memberName;
    private String content;
    private Double star;
    private Long storeId;
    private String storeName;
    private LocalDateTime createdAt;
}


