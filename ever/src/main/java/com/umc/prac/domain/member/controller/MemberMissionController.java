package com.umc.prac.domain.member.controller;

import com.umc.prac.domain.member.service.MemberMissionQueryService;
import com.umc.prac.domain.store.service.StoreCommandService;
import com.umc.prac.domain.store.dto.response.MemberMissionResponse;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Member Mission", description = "회원의 미션 관련 API")
public class MemberMissionController {

    private final MemberMissionQueryService memberMissionQueryService;
    private final StoreCommandService storeCommandService;

    public MemberMissionController(MemberMissionQueryService memberMissionQueryService, StoreCommandService storeCommandService) {
        this.memberMissionQueryService = memberMissionQueryService;
        this.storeCommandService = storeCommandService;
    }

    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "특정 회원이 도전 중이며 아직 완료하지 않은 미션 목록을 페이지 단위로 조회합니다. (한 페이지 10개)")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 진행중 미션 조회 성공",
            content = @Content(schema = @Schema(implementation = MemberMissionResponse.class)))
    @GetMapping("/members/{memberId}/missions")
    public ApiResponse<Page<MemberMissionResponse>> getOngoingMissions(@PathVariable Long memberId,
                                                                       @ValidPage @RequestParam(defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "memberMissionId"));
        Page<MemberMissionResponse> missions = memberMissionQueryService.getOngoingMissionsByMemberId(memberId, pageable);
        return ApiResponse.onSuccess(SuccessCode.MEMBER_MISSION_GET_SUCCESS, missions);
    }

    @Operation(summary = "진행중인 미션 완료 처리", description = "회원의 진행중인 미션을 완료로 변경하고 변경된 상태를 반환합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 미션 완료 처리 성공",
            content = @Content(schema = @Schema(implementation = MemberMissionResponse.class)))
    @PatchMapping("/member-missions/{memberMissionId}/complete")
    public ApiResponse<MemberMissionResponse> completeMemberMission(@PathVariable Long memberMissionId) {
        MemberMissionResponse response = storeCommandService.completeMemberMission(memberMissionId);
        return ApiResponse.onSuccess(SuccessCode.MEMBER_MISSION_COMPLETE_SUCCESS, response);
    }
}

