package com.umc.study.api.review.controller;

import com.umc.study.api.review.dto.ReviewResponseDto;
import com.umc.study.api.review.service.ReviewService;
import com.umc.study.global.annotation.ValidPage;
import com.umc.study.global.apiPayload.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "로그인한 사용자가 작성한 리뷰 목록을 페이징하여 조회합니다.")
    public ApiResponse<Page<ReviewResponseDto>> getMyReviews(
            @Parameter(description = "회원 ID", required = true) @RequestParam Long memberId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true) @ValidPage Pageable pageable) {
        Page<ReviewResponseDto> reviews = reviewService.getMyReviews(memberId, pageable);
        return ApiResponse.onSuccess(reviews);
    }
}