package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {

    public ReviewResponseDTO.CreateResultDTO toCreateResultDTO(Review review) {
        return ReviewResponseDTO.CreateResultDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .memberName(review.getMember().getName())
                .content(review.getContent())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 리뷰 -> DTO 변환 (Stream 사용)
    public ReviewResponseDTO.ReviewDTO toReviewDTO(Review review) {
        return ReviewResponseDTO.ReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .rate(review.getRate())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // Page<Review> -> ReviewListDTO 변환 (Stream 사용)
    public ReviewResponseDTO.ReviewListDTO toReviewListDTO(Page<Review> reviewPage) {
        List<ReviewResponseDTO.ReviewDTO> reviewDTOs = reviewPage.getContent()
                .stream()
                .map(this::toReviewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewListDTO.builder()
                .reviews(reviewDTOs)
                .currentPage(reviewPage.getNumber() + 1)  // 0-based -> 1-based
                .totalPages(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .build();
    }
}

