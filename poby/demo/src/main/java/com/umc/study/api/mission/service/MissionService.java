package com.umc.study.api.mission.service;

import com.umc.study.api.mission.dto.MemberMissionResponseDto;
import com.umc.study.api.mission.dto.MissionResponseDto;
import com.umc.study.api.mission.repository.MemberMissionRepository;
import com.umc.study.api.mission.repository.MissionRepository;
import com.umc.study.api.mypage.repository.MemberRepository;
import com.umc.study.domain.member.entity.Member;
import com.umc.study.domain.mission.entity.MemberMission;
import com.umc.study.domain.mission.entity.Mission;
import com.umc.study.global.apiPayload.error.dto.ErrorCode;
import com.umc.study.global.apiPayload.error.exception.BusinessException;
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
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public Page<MissionResponseDto> getStoreMissions(Long storeId, Pageable pageable) {
        return missionRepository.findByStoreId(storeId, pageable)
                .map(MissionResponseDto::from);
    }

    public Page<MemberMissionResponseDto> getMyMissions(Long memberId, Boolean isComplete, Pageable pageable) {
        return memberMissionRepository.findByMemberIdAndIsComplete(memberId, isComplete, pageable)
                .map(MemberMissionResponseDto::from);
    }
    
    @Transactional
    public MemberMissionResponseDto completeMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_MISSION_NOT_FOUND));
        
        memberMission.completeChallenge();
        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);
        
        return MemberMissionResponseDto.from(savedMemberMission);
    }
    
    @Transactional
    public MemberMission challengeMissionWithUser1(Long missionId) {
        Member user1 = memberRepository.findByEmail("chulsoo@example.com")
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MISSION_NOT_FOUND));
        
        MemberMission memberMission = MemberMission.builder()
                .member(user1)
                .mission(mission)
                .build();
        
        return memberMissionRepository.save(memberMission);
    }
}