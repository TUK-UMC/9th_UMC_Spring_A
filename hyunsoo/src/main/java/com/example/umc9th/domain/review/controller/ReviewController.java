package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰 API", description = "리뷰 관련 API")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성", description = "가게에 리뷰를 작성합니다.")
    @PostMapping
    public ApiResponse<ReviewResponseDTO.CreateResultDTO> createReview(
            @RequestBody ReviewRequestDTO.CreateDTO request
    ) {
        ReviewResponseDTO.CreateResultDTO result = reviewService.createReview(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }

    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "내가 작성한 리뷰 목록을 페이징하여 조회합니다. (한 페이지당 10개)")
    @GetMapping("/my")
    public ApiResponse<ReviewResponseDTO.ReviewListDTO> getMyReviews(
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true, example = "1")
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        ReviewResponseDTO.ReviewListDTO result = reviewService.getMyReviewList(page);
        return ApiResponse.onSuccess(result);
    }
}
