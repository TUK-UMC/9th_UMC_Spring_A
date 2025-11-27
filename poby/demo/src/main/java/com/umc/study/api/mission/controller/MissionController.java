package com.umc.study.api.mission.controller;

import com.umc.study.api.mission.dto.MemberMissionResponseDto;
import com.umc.study.api.mission.dto.MissionResponseDto;
import com.umc.study.api.mission.service.MissionService;
import com.umc.study.global.annotation.ValidPage;
import com.umc.study.global.apiPayload.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/store/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션 목록을 페이징하여 조회합니다.")
    public ApiResponse<Page<MissionResponseDto>> getStoreMissions(
            @Parameter(description = "가게 ID", required = true) @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true) @ValidPage Pageable pageable) {
        Page<MissionResponseDto> missions = missionService.getStoreMissions(storeId, pageable);
        return ApiResponse.onSuccess(missions);
    }

    @GetMapping("/my")
    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "로그인한 사용자가 진행중인 미션 목록을 페이징하여 조회합니다.")
    public ApiResponse<Page<MemberMissionResponseDto>> getMyMissions(
            @Parameter(description = "회원 ID", required = true) @RequestParam Long memberId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true) @ValidPage Pageable pageable) {
        Page<MemberMissionResponseDto> missions = missionService.getMyMissions(memberId, false, pageable);
        return ApiResponse.onSuccess(missions);
    }

    @PatchMapping("/{memberMissionId}/complete")
    @Operation(summary = "진행중인 미션을 완료로 변경", description = "진행중인 미션을 완료 상태로 변경하고 변경된 미션 정보를 반환합니다.")
    public ApiResponse<MemberMissionResponseDto> completeMission(
            @Parameter(description = "회원 미션 ID", required = true) @PathVariable Long memberMissionId) {
        MemberMissionResponseDto completedMission = missionService.completeMission(memberMissionId);
        return ApiResponse.onSuccess(completedMission);
    }
}