package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.MemberResponseDTO;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResponseDTO.SignUpResultDTO> signUp(
            @RequestBody MemberRequestDTO.SignUpDTO request
    ) {
        MemberResponseDTO.SignUpResultDTO result = memberService.signUp(request);
        return ApiResponse.onSuccess(result);
    }
}
