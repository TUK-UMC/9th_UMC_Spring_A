package com.umc.prac.domain.store.controller;

import com.umc.prac.domain.store.dto.request.MissionChallengeRequest;
import com.umc.prac.domain.store.dto.request.StoreCreateRequest;
import com.umc.prac.domain.store.dto.request.StoreMissionCreateRequest;
import com.umc.prac.domain.store.dto.request.StoreReviewCreateRequest;
import com.umc.prac.domain.store.dto.response.MemberMissionResponse;
import com.umc.prac.domain.store.dto.response.MissionResponse;
import com.umc.prac.domain.store.dto.response.ReviewResponse;
import com.umc.prac.domain.store.dto.response.StoreResponse;
import com.umc.prac.domain.store.service.StoreCommandService;
import com.umc.prac.global.apiPayload.ApiResponse;
import com.umc.prac.global.apiPayload.code.SuccessCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StoreCommandController {

    private final StoreCommandService storeCommandService;

    public StoreCommandController(StoreCommandService storeCommandService) {
        this.storeCommandService = storeCommandService;
    }

    @PostMapping("/locations/{locationId}/stores")
    public ApiResponse<StoreResponse> createStore(@PathVariable Long locationId,
                                                  @Valid @RequestBody StoreCreateRequest request) {
        StoreResponse response = storeCommandService.createStore(locationId, request);
        return ApiResponse.onSuccess(SuccessCode.STORE_CREATE_SUCCESS, response);
    }

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponse> createReview(@PathVariable Long storeId,
                                                    @Valid @RequestBody StoreReviewCreateRequest request) {
        ReviewResponse response = storeCommandService.createReview(storeId, request);
        return ApiResponse.onSuccess(SuccessCode.REVIEW_CREATE_SUCCESS, response);
    }

    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponse> createMission(@PathVariable Long storeId,
                                                      @Valid @RequestBody StoreMissionCreateRequest request) {
        MissionResponse response = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(SuccessCode.MISSION_CREATE_SUCCESS, response);
    }

    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<MemberMissionResponse> challengeMission(@PathVariable Long missionId,
                                                               @Valid @RequestBody MissionChallengeRequest request) {
        MemberMissionResponse response = storeCommandService.challengeMission(missionId, request);
        return ApiResponse.onSuccess(SuccessCode.MISSION_CHALLENGE_SUCCESS, response);
    }
}

