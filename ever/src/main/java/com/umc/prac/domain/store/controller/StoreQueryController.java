package com.umc.prac.domain.store.controller;

import com.umc.prac.domain.store.dto.response.MissionResponse;
import com.umc.prac.domain.store.service.StoreQueryService;
import com.umc.prac.global.apiPayload.ApiResponse;
import com.umc.prac.global.apiPayload.code.SuccessCode;
import com.umc.prac.global.validation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Store Query", description = "가게 관련 조회 API")
public class StoreQueryController {

    private final StoreQueryService storeQueryService;

    public StoreQueryController(StoreQueryService storeQueryService) {
        this.storeQueryService = storeQueryService;
    }

    @Operation(summary = "특정 가게의 미션 목록 조회", description = "가게의 미션을 페이지 단위로 조회합니다. (한 페이지 10개)")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "미션 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = MissionResponse.class)))
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<Page<MissionResponse>> getMissionsByStore(@PathVariable Long storeId,
                                                                 @ValidPage @RequestParam(defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<MissionResponse> missions = storeQueryService.getMissionsByStoreId(storeId, pageable);
        return ApiResponse.onSuccess(SuccessCode.MISSION_GET_SUCCESS, missions);
    }
}

