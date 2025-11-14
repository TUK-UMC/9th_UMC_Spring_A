package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.enums.ReviewEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    Page<ReviewDto> findMyReviews(Long authorId, String storeName, ReviewEnum    bucket, Pageable pageable);
}