package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberMissionResponseDTO;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    // MemberMission -> MissionDTO 변환
    public MemberMissionResponseDTO.MissionDTO toMissionDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.MissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionCondition(memberMission.getMission().getCondition())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline())
                .challengedAt(memberMission.getCreatedAt())
                .build();
    }

    // Page<MemberMission> -> MissionListDTO 변환 (Stream 사용)
    public MemberMissionResponseDTO.MissionListDTO toMissionListDTO(Page<MemberMission> memberMissionPage) {
        List<MemberMissionResponseDTO.MissionDTO> missionDTOs = memberMissionPage.getContent()
                .stream()
                .map(this::toMissionDTO)
                .collect(Collectors.toList());

        return MemberMissionResponseDTO.MissionListDTO.builder()
                .missions(missionDTOs)
                .currentPage(memberMissionPage.getNumber() + 1)  // 0-based -> 1-based
                .totalPages(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .build();
    }
}
