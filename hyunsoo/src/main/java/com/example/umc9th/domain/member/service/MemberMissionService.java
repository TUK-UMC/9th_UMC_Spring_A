package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.MemberMissionRequestDTO;
import com.example.umc9th.domain.member.dto.MemberMissionResponseDTO;

public interface MemberMissionService {
    MemberMissionResponseDTO.ChallengeResultDTO challengeMission(MemberMissionRequestDTO.ChallengeDTO request);
}
