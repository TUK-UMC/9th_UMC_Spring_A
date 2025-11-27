package com.umc.prac.domain.review.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyReviewDto {
    private Long id;
    private String content;
    private Double star;
    private String storeName;
    private LocalDateTime createdAt;
}


