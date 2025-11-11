package com.umc.study.api.review.controller;

import com.umc.study.api.review.Service.ReviewService;
import com.umc.study.api.review.dto.ReviewResponseDto;
import com.umc.study.global.apiPayload.response.CommonResponse;
import com.umc.study.global.apiPayload.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public CommonResponse<Long> createReview(
            @RequestParam Long memberId,
            @RequestParam Long storeId,
            @RequestParam String content,
            @RequestParam Float rating) {
        
        Long reviewId = reviewService.createReview(memberId, storeId, content, rating);
        return CommonResponse.of(ResultCode.CREATED, reviewId);
    }

    @GetMapping
    public CommonResponse<Page<ReviewResponseDto>> getReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingFilter,
            @PageableDefault(size = 10) Pageable pageable) {
        
        Page<ReviewResponseDto> reviews = reviewService.getReviewsWithFilters(storeName, ratingFilter, pageable);
        return CommonResponse.of(ResultCode.OK, reviews);
    }
}