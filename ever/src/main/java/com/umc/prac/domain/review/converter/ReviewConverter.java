package com.umc.prac.domain.review.converter;

import com.umc.prac.domain.review.controller.dto.MyReviewResponse;
import com.umc.prac.domain.review.controller.dto.MyReviewResponse.ReplyResponse;
import com.umc.prac.domain.review.service.dto.MyReviewDto;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class ReviewConverter {

    private ReviewConverter() {
    }

    public static Page<MyReviewResponse> toMyReviewResponses(Page<MyReviewDto> dtos,
                                                             Map<Long, List<String>> photoMap,
                                                             Map<Long, List<ReplyResponse>> replyMap) {
        return dtos.map(dto -> new MyReviewResponse(
                dto.getReviewId(),
                dto.getMemberName(),
                dto.getStoreId(),
                dto.getStoreName(),
                dto.getStar(),
                dto.getContent(),
                dto.getCreatedAt(),
                photoMap.getOrDefault(dto.getReviewId(), Collections.emptyList()),
                replyMap.getOrDefault(dto.getReviewId(), Collections.emptyList())
        ));
    }
}

