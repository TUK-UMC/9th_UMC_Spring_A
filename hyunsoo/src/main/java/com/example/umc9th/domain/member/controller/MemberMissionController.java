package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberMissionRequestDTO;
import com.example.umc9th.domain.member.dto.MemberMissionResponseDTO;
import com.example.umc9th.domain.member.service.MemberMissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 미션 API", description = "회원의 미션 도전 관련 API")
@RestController
@RequestMapping("/api/member-missions")
@RequiredArgsConstructor
@Validated
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @Operation(summary = "미션 도전하기", description = "특정 미션에 도전합니다.")
    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponseDTO.ChallengeResultDTO> challengeMission(
            @RequestBody MemberMissionRequestDTO.ChallengeDTO request
    ) {
        MemberMissionResponseDTO.ChallengeResultDTO result = memberMissionService.challengeMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }

    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "내가 현재 진행중인 미션 목록을 페이징하여 조회합니다. (한 페이지당 10개)")
    @GetMapping("/ongoing")
    public ApiResponse<MemberMissionResponseDTO.MissionListDTO> getMyOngoingMissions(
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true, example = "1")
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        MemberMissionResponseDTO.MissionListDTO result = memberMissionService.getMyOngoingMissions(page);
        return ApiResponse.onSuccess(result);
    }
}

