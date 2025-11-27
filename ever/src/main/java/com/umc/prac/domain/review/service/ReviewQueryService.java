package com.umc.prac.domain.review.service;

import com.umc.prac.domain.review.controller.dto.MyReviewResponse;
import com.umc.prac.domain.review.controller.dto.MyReviewResponse.ReplyResponse;
import com.umc.prac.domain.review.converter.ReviewConverter;
import com.umc.prac.domain.review.entity.Reply;
import com.umc.prac.domain.review.entity.ReviewPhoto;
import com.umc.prac.domain.review.repository.ReplyRepository;
import com.umc.prac.domain.review.repository.ReviewPhotoRepository;
import com.umc.prac.domain.review.repository.ReviewRepository;
import com.umc.prac.domain.review.service.dto.MyReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final ReplyRepository replyRepository;

    public ReviewQueryService(ReviewRepository reviewRepository,
                              ReviewPhotoRepository reviewPhotoRepository,
                              ReplyRepository replyRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewPhotoRepository = reviewPhotoRepository;
        this.replyRepository = replyRepository;
    }

    public Page<MyReviewResponse> getMyReviews(Long memberId, Long storeId, String storeName, Integer starGroup, Pageable pageable) {
        Page<MyReviewDto> myReviews = reviewRepository.findMyReviews(memberId, storeId, storeName, starGroup, pageable);
        List<MyReviewDto> contents = myReviews.getContent();
        List<Long> reviewIds = contents.stream()
                .map(MyReviewDto::getReviewId)
                .toList();

        Map<Long, List<String>> photoMap = reviewIds.isEmpty() ? Map.of() : loadPhotoUrls(reviewIds);
        Map<Long, List<ReplyResponse>> replyMap = reviewIds.isEmpty() ? Map.of() : loadReplies(reviewIds);

        return ReviewConverter.toMyReviewResponses(myReviews, photoMap, replyMap);
    }

    private Map<Long, List<String>> loadPhotoUrls(List<Long> reviewIds) {
        List<ReviewPhoto> photos = reviewPhotoRepository.findByReviewReviewIdIn(reviewIds);
        return photos.stream()
                .collect(Collectors.groupingBy(
                        photo -> photo.getReview().getReviewId(),
                        Collectors.mapping(ReviewPhoto::getUrl, Collectors.toList())
                ));
    }

    private Map<Long, List<ReplyResponse>> loadReplies(List<Long> reviewIds) {
        List<Reply> replies = replyRepository.findByReviewReviewIdIn(reviewIds);
        return replies.stream()
                .collect(Collectors.groupingBy(
                        reply -> reply.getReview().getReviewId(),
                        Collectors.mapping(toReplyResponse(), Collectors.toList())
                ));
    }

    private Function<Reply, ReplyResponse> toReplyResponse() {
        return reply -> new ReplyResponse(reply.getReplyId(), reply.getContent());
    }
}


