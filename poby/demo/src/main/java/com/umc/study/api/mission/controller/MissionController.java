package com.umc.study.api.mission.controller;

import com.umc.study.api.mission.dto.MemberMissionResponseDto;
import com.umc.study.api.mission.service.MissionService;
import com.umc.study.global.apiPayload.response.CommonResponse;
import com.umc.study.global.apiPayload.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/my")
    public CommonResponse<Page<MemberMissionResponseDto>> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam(required = false) Boolean isComplete,
            @PageableDefault(size = 10) Pageable pageable) {
        
        Page<MemberMissionResponseDto> missions = missionService.getMyMissions(memberId, isComplete, pageable);
        return CommonResponse.of(ResultCode.OK, missions);
    }
}