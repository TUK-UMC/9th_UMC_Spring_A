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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Store Command", description = "가게/리뷰/미션 생성 API")
public class StoreCommandController {

    private final StoreCommandService storeCommandService;

    public StoreCommandController(StoreCommandService storeCommandService) {
        this.storeCommandService = storeCommandService;
    }

    @Operation(summary = "지역별 가게 등록", description = "특정 지역(locationId)에 가게를 등록합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "가게 등록 성공",
            content = @Content(schema = @Schema(implementation = StoreResponse.class)))
    @PostMapping("/locations/{locationId}/stores")
    public ApiResponse<StoreResponse> createStore(@PathVariable Long locationId,
                                                  @Valid @RequestBody StoreCreateRequest request) {
        StoreResponse response = storeCommandService.createStore(locationId, request);
        return ApiResponse.onSuccess(SuccessCode.STORE_CREATE_SUCCESS, response);
    }

    @Operation(summary = "가게 리뷰 등록", description = "특정 가게(storeId)에 회원이 리뷰를 작성합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "리뷰 등록 성공",
            content = @Content(schema = @Schema(implementation = ReviewResponse.class)))
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponse> createReview(@PathVariable Long storeId,
                                                    @Valid @RequestBody StoreReviewCreateRequest request) {
        ReviewResponse response = storeCommandService.createReview(storeId, request);
        return ApiResponse.onSuccess(SuccessCode.REVIEW_CREATE_SUCCESS, response);
    }

    @Operation(summary = "가게 미션 등록", description = "특정 가게(storeId)에 미션을 추가합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "미션 등록 성공",
            content = @Content(schema = @Schema(implementation = MissionResponse.class)))
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponse> createMission(@PathVariable Long storeId,
                                                      @Valid @RequestBody StoreMissionCreateRequest request) {
        MissionResponse response = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(SuccessCode.MISSION_CREATE_SUCCESS, response);
    }

    @Operation(summary = "미션 도전 등록", description = "회원이 특정 미션(missionId)을 도전 목록에 추가합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "미션 도전 성공",
            content = @Content(schema = @Schema(implementation = MemberMissionResponse.class)))
    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<MemberMissionResponse> challengeMission(@PathVariable Long missionId,
                                                               @Valid @RequestBody MissionChallengeRequest request) {
        MemberMissionResponse response = storeCommandService.challengeMission(missionId, request);
        return ApiResponse.onSuccess(SuccessCode.MISSION_CHALLENGE_SUCCESS, response);
    }
}

