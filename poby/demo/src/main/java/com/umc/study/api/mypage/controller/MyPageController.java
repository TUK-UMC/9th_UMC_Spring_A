package com.umc.study.api.mypage.controller;

import com.umc.study.api.mypage.dto.MyPageResponse;
import com.umc.study.api.mypage.service.MyPageService;
import com.umc.study.global.apiPayload.response.CommonResponse;
import com.umc.study.global.apiPayload.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/{memberId}")
    public CommonResponse<MyPageResponse> getMyPage(@PathVariable Long memberId) {
        MyPageResponse myPage = myPageService.getMyPage(memberId);
        return CommonResponse.of(ResultCode.USER_FETCH_OK, myPage);
    }
}