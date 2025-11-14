package com.umc.prac.domain.review.repository;

import com.umc.prac.domain.review.service.dto.MyReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<MyReviewDto> findMyReviews(Long memberId, Long storeId, String storeName, Integer starGroup, Pageable pageable);
}


