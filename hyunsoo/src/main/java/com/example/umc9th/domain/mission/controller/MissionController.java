package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "미션 API", description = "미션 관련 API")
@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
@Validated
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션 목록을 페이징하여 조회합니다. (한 페이지당 10개)")
    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getStoreMissions(
            @Parameter(description = "가게 ID", required = true, example = "1")
            @PathVariable(name = "storeId") Long storeId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true, example = "1")
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        MissionResponseDTO.MissionListDTO result = missionService.getStoreMissions(storeId, page);
        return ApiResponse.onSuccess(result);
    }
}
