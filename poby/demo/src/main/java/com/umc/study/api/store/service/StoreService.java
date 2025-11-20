package com.umc.study.api.store.service;

import com.umc.study.api.mypage.repository.MemberRepository;
import com.umc.study.api.store.dto.ReviewRequestDto;
import com.umc.study.api.store.repository.StoreRepository;
import com.umc.study.api.review.repository.ReviewRepository;
import com.umc.study.domain.member.entity.Member;
import com.umc.study.domain.review.entity.Review;
import com.umc.study.domain.store.entity.Store;
import com.umc.study.global.apiPayload.error.dto.ErrorCode;
import com.umc.study.global.apiPayload.error.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreService {
    
    @Autowired
    private StoreRepository storeRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Transactional
    public Review addReview(Long storeId, Long memberId, ReviewRequestDto requestDto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.STORE_NOT_FOUND));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        
        Review review = Review.builder()
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .store(store)
                .member(member)
                .build();
        
        return reviewRepository.save(review);
    }
}