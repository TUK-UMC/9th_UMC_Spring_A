package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final EntityManager em;  // FK 프록시 생성용

    public Long writeReview(Long memberId, Long storeId, String content, Float rate) {

        Member memberRef = em.getReference(Member.class, memberId);
        Store storeRef = em.getReference(Store.class, storeId);

        // 리뷰 엔티티 생성 및 저장
        Review review = Review.builder()
                .content(content)
                .rate(rate)
                .member(memberRef)
                .store(storeRef)
                .build();

        return reviewRepository.save(review).getId();
    }
}
