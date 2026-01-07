package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl extends MemberCommandService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Override
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ){
        return null;
    }
}