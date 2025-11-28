package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionConverter {

    // Mission -> MissionDTO 변환
    public MissionResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .condition(mission.getCondition())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    // Page<Mission> -> MissionListDTO 변환 (Stream 사용)
    public MissionResponseDTO.MissionListDTO toMissionListDTO(Page<Mission> missionPage) {
        List<MissionResponseDTO.MissionDTO> missionDTOs = missionPage.getContent()
                .stream()
                .map(this::toMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionListDTO.builder()
                .missions(missionDTOs)
                .currentPage(missionPage.getNumber() + 1)  // 0-based -> 1-based
                .totalPages(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .build();
    }
}
