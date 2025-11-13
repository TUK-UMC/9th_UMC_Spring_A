package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.QMember;
import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.enums.ReviewEnum;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QReview review = QReview.review;
    private static final QStore store = QStore.store;
    private static final QMember member = QMember.member;

    @Override
    public Page<ReviewDto> findMyReviews(Long authorId, String storeName, ReviewEnum bucket, Pageable pageable) {
        List<ReviewDto> content = queryFactory
                .select(Projections.constructor(ReviewDto.class,
                        review.id,
                        store.name,
                        review.rate,
                        review.content,
                        review.createdAt
                ))
                .from(review)
                .join(review.store, store)
                .join(review.member, member)
                .where(
                        review.member.id.eq(authorId),
                        storeNameEq(storeName),
                        starBucketCond(bucket)
                )
                .orderBy(review.createdAt.desc(), review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(review.count())
                .from(review)
                .join(review.member, member)
                .where(
                        review.member.id.eq(authorId),
                        storeNameEq(storeName),
                        starBucketCond(bucket)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression storeNameEq(String storeName) {
        return (storeName != null && !storeName.isBlank()) ? store.name.eq(storeName) : null;
    }

    private BooleanExpression starBucketCond(ReviewEnum b) {
        if (b == null || b == ReviewEnum.ALL) return null;
        return switch (b) {
            case FIVE  -> review.rate.eq(5.0f);
            case FOUR  -> review.rate.goe(4.0f).and(review.rate.lt(5.0f));
            case THREE -> review.rate.goe(3.0f).and(review.rate.lt(4.0f));
            case TWO   -> review.rate.goe(2.0f).and(review.rate.lt(3.0f));
            case ONE   -> review.rate.goe(1.0f).and(review.rate.lt(2.0f));
            default    -> null;
        };
    }
}
