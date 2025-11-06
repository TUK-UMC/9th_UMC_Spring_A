package com.umc.prac.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.prac.domain.review.entity.QReply;
import com.umc.prac.domain.review.entity.QReview;
import com.umc.prac.domain.review.service.dto.MyReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<MyReviewDto> findMyReviews(Long memberId, Long storeId, String storeName, Integer starGroup, Pageable pageable) {
        QReview review = QReview.review;
        QReply reply = QReply.reply;

        Double minStar = null;
        Double maxStar = null;
        if (starGroup != null) {
            if (starGroup == 5) { // 5점만
                minStar = 5.0; maxStar = 5.0;
            } else {
                minStar = starGroup.doubleValue();
                maxStar = starGroup + 0.9;
            }
        }

        List<MyReviewDto> contents = queryFactory
                .select(Projections.constructor(
                        MyReviewDto.class,
                        review.reviewId,
                        review.content,
                        review.star,
                        review.store.name,
                        review.createdAt
                ))
                .from(review)
                .leftJoin(review.replies, reply).on(reply.review.eq(review))
                .where(
                        review.member.memberId.eq(memberId),
                        storeIdEq(storeId, review),
                        storeNameEq(storeName, review),
                        starBetween(minStar, maxStar, review)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(
                        review.member.memberId.eq(memberId),
                        storeIdEq(storeId, review),
                        storeNameEq(storeName, review),
                        starBetween(minStar, maxStar, review)
                )
                .fetchOne();

        long totalCount = total == null ? 0L : total;
        return new PageImpl<>(contents, pageable, totalCount);
    }

    private BooleanExpression storeIdEq(Long storeId, QReview review) {
        return storeId == null ? null : review.store.storeId.eq(storeId);
    }

    private BooleanExpression storeNameEq(String storeName, QReview review) {
        return (storeName == null || storeName.isBlank()) ? null : review.store.name.contains(storeName);
    }

    private BooleanExpression starBetween(Double min, Double max, QReview review) {
        if (min == null || max == null) return null;
        if (min.equals(max)) return review.star.eq(min);
        return review.star.between(min, max);
    }
}


