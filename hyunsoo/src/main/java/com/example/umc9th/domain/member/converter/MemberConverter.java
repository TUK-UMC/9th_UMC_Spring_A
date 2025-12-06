package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.dto.MyReviewResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .gender(dto.gender())
                .build();
    }

    // Review List -> ReviewListDTO (Stream 사용)
    public MyReviewResponseDTO.ReviewListDTO toReviewListDTO(Page<Review> reviewPage) {
        List<MyReviewResponseDTO.ReviewItem> reviewItems = reviewPage.getContent()
                .stream()
                .map(review -> MyReviewResponseDTO.ReviewItem.builder()
                        .memberName(review.getMember().getName())
                        .rating(review.getRate())
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return MyReviewResponseDTO.ReviewListDTO.builder()
                .reviews(reviewItems)
                .currentPage(reviewPage.getNumber() + 1)  // 0-based -> 1-based
                .totalPages(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .build();
    }
}