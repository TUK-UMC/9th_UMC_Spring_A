package com.umc.study.api.mission.service;

import com.umc.study.api.mission.dto.MemberMissionResponseDto;
import com.umc.study.api.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public Page<MemberMissionResponseDto> getMyMissions(Long memberId, Boolean isComplete, Pageable pageable) {
        return memberMissionRepository.findByMemberIdAndIsComplete(memberId, isComplete, pageable)
                .map(MemberMissionResponseDto::from);
    }
}