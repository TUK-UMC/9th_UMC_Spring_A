package com.umc.prac.domain.review.controller;

import com.umc.prac.domain.review.controller.dto.MyReviewResponse;
import com.umc.prac.domain.review.service.ReviewQueryService;
import com.umc.prac.global.apiPayload.ApiResponse;
import com.umc.prac.global.apiPayload.code.SuccessCode;
import com.umc.prac.global.validation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@Tag(name = "Review", description = "리뷰 조회 API")
@Validated
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    public ReviewController(ReviewQueryService reviewQueryService) {
        this.reviewQueryService = reviewQueryService;
    }

    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "가게/별점 필터와 함께 내가 작성한 리뷰를 10개 단위로 페이지 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 조회 성공",
            content = @Content(schema = @Schema(implementation = MyReviewResponse.class)))
    @GetMapping("/me")
    public ApiResponse<Page<MyReviewResponse>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer starGroup,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<MyReviewResponse> reviews = reviewQueryService.getMyReviews(memberId, storeId, storeName, starGroup, pageable);
        return ApiResponse.onSuccess(SuccessCode.REVIEW_GET_SUCCESS, reviews);
    }
}


