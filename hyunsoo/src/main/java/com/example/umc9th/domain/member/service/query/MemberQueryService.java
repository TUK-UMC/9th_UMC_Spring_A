package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.MyReviewResponseDTO;

public interface MemberQueryService {
    MyReviewResponseDTO.ReviewListDTO getMyReviews(Integer page);
}
