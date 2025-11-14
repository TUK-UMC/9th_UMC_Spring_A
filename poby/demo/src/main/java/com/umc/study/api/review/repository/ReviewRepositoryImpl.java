package com.umc.study.api.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.study.api.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.umc.study.domain.review.entity.QReview.review;
import static com.umc.study.domain.store.entity.QStore.store;
import static com.umc.study.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewResponseDto> findReviewsWithFilters(String storeName, Integer ratingFilter, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        
        // 삭제되지 않은 리뷰만 조회
        builder.and(review.isDeleted.eq(false));
        
        // 가게명 필터링
        if (storeName != null && !storeName.trim().isEmpty()) {
            builder.and(store.name.containsIgnoreCase(storeName));
        }
        
        // 별점 필터링 (5점, 4점대, 3점대, 2점대, 1점대)
        if (ratingFilter != null) {
            switch (ratingFilter) {
                case 5:
                    builder.and(review.rating.eq(5.0f));
                    break;
                case 4:
                    builder.and(review.rating.goe(4.0f).and(review.rating.lt(5.0f)));
                    break;
                case 3:
                    builder.and(review.rating.goe(3.0f).and(review.rating.lt(4.0f)));
                    break;
                case 2:
                    builder.and(review.rating.goe(2.0f).and(review.rating.lt(3.0f)));
                    break;
                case 1:
                    builder.and(review.rating.goe(1.0f).and(review.rating.lt(2.0f)));
                    break;
            }
        }

        JPAQuery<ReviewResponseDto> query = queryFactory
                .select(Projections.constructor(ReviewResponseDto.class,
                        review.id,
                        review.content,
                        review.rating,
                        member.name,
                        store.name,
                        review.createdAt))
                .from(review)
                .join(review.store, store)
                .join(review.member, member)
                .where(builder)
                .orderBy(review.createdAt.desc());

        List<ReviewResponseDto> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(review)
                .join(review.store, store)
                .join(review.member, member)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }
}