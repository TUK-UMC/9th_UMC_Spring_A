package com.umc.prac.domain.store.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StoreReviewCreateRequest(
        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId,

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        String content,

        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "0.0", message = "별점은 0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5 이하이어야 합니다.")
        Double star
) {
}

