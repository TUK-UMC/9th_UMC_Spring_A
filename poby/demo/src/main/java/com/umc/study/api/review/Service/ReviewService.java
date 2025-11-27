package com.umc.study.api.review.service;

import com.umc.study.api.review.dto.ReviewResponseDto;
import com.umc.study.api.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Page<ReviewResponseDto> getMyReviews(Long memberId, Pageable pageable) {
        return reviewRepository.findByMemberId(memberId, pageable);
    }
}