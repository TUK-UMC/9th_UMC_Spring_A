package com.umc.study.api.review.Service;

import com.umc.study.api.review.repository.ReviewRepository;
import com.umc.study.domain.review.entity.Review;
import com.umc.study.domain.member.entity.Member;
import com.umc.study.domain.store.entity.Store;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

  private final EntityManager em;
  private final ReviewRepository reviewRepository;


  public Long createReview(Long memberId, Long storeId, String content, Float rating) {
    Member memberRef = em.getReference(Member.class, memberId);
    Store storeRef = em.getReference(Store.class, storeId);

    Review review = Review.builder()
        .content(content)
        .rating(rating)
        .isDeleted(false)
        .member(memberRef)
        .store(storeRef)
        .build();

    return reviewRepository.save(review).getId();
  }

}