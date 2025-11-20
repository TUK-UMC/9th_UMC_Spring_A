package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberMissionResponseDTO;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import org.springframework.stereotype.Component;

@Component
public class MemberMissionConverter {

    public MemberMissionResponseDTO.ChallengeResultDTO toChallengeResultDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.ChallengeResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberName(memberMission.getMember().getName())
                .storeName(memberMission.getMission().getStore().getName())
                .missionCondition(memberMission.getMission().getCondition())
                .missionDeadline(memberMission.getMission().getDeadline())
                .point(memberMission.getMission().getPoint())
                .isComplete(memberMission.getIsComplete())
                .challengedAt(memberMission.getCreatedAt())
                .build();
    }
}
