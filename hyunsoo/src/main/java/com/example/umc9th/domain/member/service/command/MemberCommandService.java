package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO.JoinDTO;

public abstract class MemberCommandService {
    // 회원가입
    public abstract JoinDTO signup(MemberReqDTO.JoinDTO dto);
}
