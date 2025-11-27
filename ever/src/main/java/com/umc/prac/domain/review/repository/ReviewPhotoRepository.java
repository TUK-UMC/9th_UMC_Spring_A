package com.umc.prac.domain.review.repository;

import com.umc.prac.domain.review.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {

    List<ReviewPhoto> findByReviewReviewIdIn(List<Long> reviewIds);
}

