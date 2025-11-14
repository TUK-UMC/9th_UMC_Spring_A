package com.umc.prac.domain.review.service;

import com.umc.prac.domain.review.repository.ReviewRepository;
import com.umc.prac.domain.review.service.dto.MyReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewQueryService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Page<MyReviewDto> getMyReviews(Long memberId, Long storeId, String storeName, Integer starGroup, Pageable pageable) {
        return reviewRepository.findMyReviews(memberId, storeId, storeName, starGroup, pageable);
    }
}


