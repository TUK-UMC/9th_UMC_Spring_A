package com.umc.prac.domain.review.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public record MyReviewResponse(
        Long reviewId,
        String memberName,
        Long storeId,
        String storeName,
        Double star,
        String content,
        LocalDateTime createdAt,
        List<String> photoUrls,
        List<ReplyResponse> replies
) {
    public record ReplyResponse(Long replyId, String content) {}
}

