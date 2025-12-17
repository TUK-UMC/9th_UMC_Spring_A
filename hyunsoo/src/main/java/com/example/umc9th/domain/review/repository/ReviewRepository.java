package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {
    
    // 특정 회원의 리뷰 목록 조회 (페이징, 최신순)
    Page<Review> findByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);
}