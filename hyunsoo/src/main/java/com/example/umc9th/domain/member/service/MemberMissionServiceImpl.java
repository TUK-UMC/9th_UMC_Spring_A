package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberMissionConverter;
import com.example.umc9th.domain.member.dto.MemberMissionRequestDTO;
import com.example.umc9th.domain.member.dto.MemberMissionResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.member.repository.mapping.MemberMissionRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionServiceImpl implements MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionConverter memberMissionConverter;

    @Override
    @Transactional
    public MemberMissionResponseDTO.ChallengeResultDTO challengeMission(MemberMissionRequestDTO.ChallengeDTO request) {
        // 1. 하드코딩된 유저 조회 (DB에 있는 첫 번째 회원)
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다. DB에 회원 데이터를 추가해주세요."));

        // 2. 미션 조회
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new RuntimeException("해당 미션을 찾을 수 없습니다."));

        // 3. 이미 도전중인 미션인지 확인
        if (memberMissionRepository.existsByMemberIdAndMissionId(member.getId(), mission.getId())) {
            throw new RuntimeException("이미 도전중인 미션입니다.");
        }

        // 4. MemberMission 엔티티 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();

        // 5. DB 저장
        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);

        // 6. 응답 DTO 변환
        return memberMissionConverter.toChallengeResultDTO(savedMemberMission);
    }

    @Override
    public MemberMissionResponseDTO.MissionListDTO getMyOngoingMissions(Integer page) {
        // 1. 하드코딩된 유저 조회
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        // 2. 페이징 설정 (page는 1-based, PageRequest는 0-based)
        Pageable pageable = PageRequest.of(page - 1, 10);

        // 3. 진행중인 미션 목록 조회 (isComplete = false)
        Page<MemberMission> memberMissionPage = memberMissionRepository
                .findByMemberIdAndIsCompleteFalseOrderByCreatedAtDesc(member.getId(), pageable);

        // 4. DTO 변환
        return memberMissionConverter.toMissionListDTO(memberMissionPage);
    }
}
