package com.umc.prac.domain.member.controller;

import com.umc.prac.domain.member.dto.*;
import com.umc.prac.domain.member.service.MemberCommandService;
import com.umc.prac.global.apiPayload.ApiResponse;
import com.umc.prac.global.apiPayload.code.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "회원가입/로그인(세션 & JWT) API")
public class AuthController {

    private final MemberCommandService memberCommandService;

    public AuthController(MemberCommandService memberCommandService) {
        this.memberCommandService = memberCommandService;
    }

    // 세션 방식 회원가입
    @Operation(summary = "회원가입 (세션/JWT 공통)", description = "새 회원을 등록합니다.")
    @PostMapping("/join")
    public ApiResponse<MemberJoinResponse> join(@Valid @RequestBody MemberJoinRequest request) {
        MemberJoinResponse response = memberCommandService.join(request);
        return ApiResponse.onSuccess(SuccessCode.OK, response);
    }

    // 세션 로그인
    @Operation(summary = "로그인 (세션)", description = "세션 기반 로그인 후 JSESSIONID 쿠키를 발급합니다.")
    @PostMapping("/session/login")
    public ApiResponse<SessionLoginResponse> sessionLogin(@Valid @RequestBody LoginRequest request,
                                                          HttpServletRequest httpRequest) {
        SessionLoginResponse response = memberCommandService.sessionLogin(request, httpRequest);
        return ApiResponse.onSuccess(SuccessCode.OK, response);
    }

    // 세션 로그아웃
    @Operation(summary = "로그아웃 (세션)", description = "현재 세션을 무효화합니다.")
    @PostMapping("/session/logout")
    public ApiResponse<Void> sessionLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ApiResponse.onSuccess(SuccessCode.OK);
    }

    // JWT 로그인
    @Operation(summary = "로그인 (JWT)", description = "JWT 액세스 토큰을 발급합니다.")
    @PostMapping("/jwt/login")
    public ApiResponse<JwtLoginResponse> jwtLogin(@Valid @RequestBody LoginRequest request) {
        JwtLoginResponse response = memberCommandService.jwtLogin(request);
        return ApiResponse.onSuccess(SuccessCode.OK, response);
    }
}


