package com.umc.prac.domain.review.controller;

import com.umc.prac.domain.review.service.ReviewQueryService;
import com.umc.prac.domain.review.service.dto.MyReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    public ReviewController(ReviewQueryService reviewQueryService) {
        this.reviewQueryService = reviewQueryService;
    }

    // 하나의 API에서 가게/별점 필터를 모두 처리
    // 예) GET /api/v1/reviews/me?memberId=1&storeId=2&starGroup=4&page=0&size=10
    //     starGroup: 5 | 4 | 3 | 2 | 1 (5는 5.0만, 그 외는 N.0 ~ N.9)
    @GetMapping("/me")
    public Page<MyReviewDto> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer starGroup,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return reviewQueryService.getMyReviews(memberId, storeId, storeName, starGroup, pageable);
    }
}


