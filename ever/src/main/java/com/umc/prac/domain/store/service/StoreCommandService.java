package com.umc.prac.domain.store.service;

import com.umc.prac.domain.member.entity.Member;
import com.umc.prac.domain.member.entity.MemberMission;
import com.umc.prac.domain.member.repository.MemberMissionRepository;
import com.umc.prac.domain.member.repository.MemberRepository;
import com.umc.prac.domain.mission.entity.Mission;
import com.umc.prac.domain.mission.repository.MissionRepository;
import com.umc.prac.domain.review.entity.Review;
import com.umc.prac.domain.review.repository.ReviewRepository;
import com.umc.prac.domain.store.dto.request.MissionChallengeRequest;
import com.umc.prac.domain.store.dto.request.StoreCreateRequest;
import com.umc.prac.domain.store.dto.request.StoreMissionCreateRequest;
import com.umc.prac.domain.store.dto.request.StoreReviewCreateRequest;
import com.umc.prac.domain.store.dto.response.MemberMissionResponse;
import com.umc.prac.domain.store.dto.response.MissionResponse;
import com.umc.prac.domain.store.dto.response.ReviewResponse;
import com.umc.prac.domain.store.dto.response.StoreResponse;
import com.umc.prac.domain.store.entity.Location;
import com.umc.prac.domain.store.entity.Store;
import com.umc.prac.domain.store.repository.LocationRepository;
import com.umc.prac.domain.store.repository.StoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class StoreCommandService {

    private final LocationRepository locationRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public StoreCommandService(LocationRepository locationRepository,
                               StoreRepository storeRepository,
                               ReviewRepository reviewRepository,
                               MissionRepository missionRepository,
                               MemberRepository memberRepository,
                               MemberMissionRepository memberMissionRepository) {
        this.locationRepository = locationRepository;
        this.storeRepository = storeRepository;
        this.reviewRepository = reviewRepository;
        this.missionRepository = missionRepository;
        this.memberRepository = memberRepository;
        this.memberMissionRepository = memberMissionRepository;
    }

    public StoreResponse createStore(Long locationId, StoreCreateRequest request) {
        Location location = getLocation(locationId);

        Store store = Store.builder()
                .name(request.name())
                .managerNumber(request.managerNumber())
                .location(location)
                .build();

        Store saved = storeRepository.save(store);
        return new StoreResponse(saved.getStoreId(), saved.getName(), location.getLocationId(), saved.getManagerNumber());
    }

    public ReviewResponse createReview(Long storeId, StoreReviewCreateRequest request) {
        Store store = getStore(storeId);
        Member member = getMember(request.memberId());

        Review review = Review.builder()
                .store(store)
                .member(member)
                .content(request.content())
                .star(request.star())
                .build();

        Review saved = reviewRepository.save(review);
        return new ReviewResponse(saved.getReviewId(), member.getMemberId(), store.getStoreId(),
                saved.getStar(), saved.getContent(), saved.getCreatedAt());
    }

    public MissionResponse createMission(Long storeId, StoreMissionCreateRequest request) {
        Store store = getStore(storeId);

        Mission mission = Mission.builder()
                .store(store)
                .conditional(request.conditional())
                .point(request.point())
                .deadline(request.deadline())
                .build();

        Mission saved = missionRepository.save(mission);
        return new MissionResponse(saved.getMissionId(), store.getStoreId(), saved.getConditional(),
                saved.getPoint(), saved.getDeadline(), saved.getCreatedAt());
    }

    public MemberMissionResponse challengeMission(Long missionId, MissionChallengeRequest request) {
        Mission mission = getMission(missionId);
        Member member = getMember(request.memberId());

        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 도전 중인 미션입니다.");
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();

        MemberMission saved = memberMissionRepository.save(memberMission);
        return new MemberMissionResponse(saved.getMemberMissionId(), member.getMemberId(), mission.getMissionId(), saved.getIsComplete());
    }

    // 특정 회원미션을 완료 처리하고 변경된 상태 반환
    public MemberMissionResponse completeMemberMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "존재하지 않는 회원 미션입니다."));

        if (memberMission.getIsComplete()) {
            // 이미 완료된 미션이면 conflict
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.CONFLICT, "이미 완료된 미션입니다.");
        }

        memberMission.setIsComplete(true);
        MemberMission saved = memberMissionRepository.save(memberMission);
        return new MemberMissionResponse(saved.getMemberMissionId(), saved.getMember().getMemberId(), saved.getMission().getMissionId(), saved.getIsComplete());
    }

    private Location getLocation(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 지역입니다."));
    }

    private Store getStore(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 가게입니다."));
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."));
    }

    private Mission getMission(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 미션입니다."));
    }
}
