package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.MemberResponseDTO;

public interface MemberService {
    MemberResponseDTO.SignUpResultDTO signUp(MemberRequestDTO.SignUpDTO request);
}
