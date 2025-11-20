package com.umc.study.api.store.controller;

import com.umc.study.api.store.dto.ReviewRequestDto;
import com.umc.study.api.store.service.StoreService;
import com.umc.study.domain.review.entity.Review;
import com.umc.study.global.apiPayload.response.CommonResponse;
import com.umc.study.global.apiPayload.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
public class StoreController {
    
    @Autowired
    private StoreService storeService;
    
    @PostMapping("/{storeId}/reviews")
    public CommonResponse<Review> addReview(@PathVariable Long storeId, 
                           @RequestParam Long memberId,
                           @RequestBody ReviewRequestDto requestDto) {
        Review review = storeService.addReview(storeId, memberId, requestDto);
        return CommonResponse.of(ResultCode.REVIEW_CREATE_OK, review);
    }
}