package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.enums.ReviewEnum;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repo;

    public Page<ReviewDto> getMyReviews(Long me, String storeName, String starBucket, Pageable pageable) {
        return repo.findMyReviews(me, storeName, ReviewEnum.from(starBucket), pageable);
    }
}
