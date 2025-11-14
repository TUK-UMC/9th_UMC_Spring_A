package com.umc.study.api.review.repository;

import com.umc.study.api.review.dto.ReviewResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<ReviewResponseDto> findReviewsWithFilters(String storeName, Integer ratingFilter, Pageable pageable);
}