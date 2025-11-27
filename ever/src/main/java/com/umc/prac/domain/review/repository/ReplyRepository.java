package com.umc.prac.domain.review.repository;

import com.umc.prac.domain.review.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByReviewReviewIdIn(List<Long> reviewIds);
}

