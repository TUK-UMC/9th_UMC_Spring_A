package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberMissionRequestDTO;
import com.example.umc9th.domain.member.dto.MemberMissionResponseDTO;
import com.example.umc9th.domain.member.service.MemberMissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member-missions")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponseDTO.ChallengeResultDTO> challengeMission(
            @RequestBody MemberMissionRequestDTO.ChallengeDTO request
    ) {
        MemberMissionResponseDTO.ChallengeResultDTO result = memberMissionService.challengeMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }
}
