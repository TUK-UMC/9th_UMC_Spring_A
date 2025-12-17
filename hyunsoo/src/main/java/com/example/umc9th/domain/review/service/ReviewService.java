package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.enums.ReviewEnum;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository repo;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewConverter reviewConverter;

    public Page<ReviewDto> getMyReviews(Long me, String storeName, String starBucket, Pageable pageable) {
        return repo.findMyReviews(me, storeName, ReviewEnum.from(starBucket), pageable);
    }

    @Transactional
    public ReviewResponseDTO.CreateResultDTO createReview(ReviewRequestDTO.CreateDTO request) {
        // 1. 가게 조회
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("해당 가게를 찾을 수 없습니다."));

        // 2. 하드코딩된 유저 조회 (DB에 있는 첫 번째 회원)
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다. DB에 회원 데이터를 추가해주세요."));

        // 3. Review 엔티티 생성
        Review review = Review.builder()
                .content(request.getContent())
                .rate(request.getRate())
                .store(store)
                .member(member)
                .build();

        // 4. DB 저장
        Review savedReview = repo.save(review);

        // 5. 응답 DTO 변환
        return reviewConverter.toCreateResultDTO(savedReview);
    }

    // 내가 작성한 리뷰 목록 조회 (페이징)
    public ReviewResponseDTO.ReviewListDTO getMyReviewList(Integer page) {
        // 1. 하드코딩된 유저 조회
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        // 2. 페이징 설정 (page는 1-based, PageRequest는 0-based)
        Pageable pageable = PageRequest.of(page - 1, 10);

        // 3. 리뷰 목록 조회
        Page<Review> reviewPage = repo.findByMemberIdOrderByCreatedAtDesc(member.getId(), pageable);

        // 4. DTO 변환
        return reviewConverter.toReviewListDTO(reviewPage);
    }
}
