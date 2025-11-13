package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    @Override
    @Transactional
    public MemberResponseDTO.SignUpResultDTO signUp(MemberRequestDTO.SignUpDTO request) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        // Member 엔티티 생성 (간단한 버전)
        Member member = Member.builder()
                .name(request.getUsername())
                .email(request.getEmail())
                .point(0)
                .build();

        // DB 저장
        Member savedMember = memberRepository.save(member);

        // 응답 DTO 변환
        return memberConverter.toSignUpResultDTO(savedMember);
    }
}
