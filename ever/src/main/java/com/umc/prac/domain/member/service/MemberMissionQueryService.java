package com.umc.prac.domain.member.service;

import com.umc.prac.domain.member.entity.Member;
import com.umc.prac.domain.member.entity.MemberMission;
import com.umc.prac.domain.member.repository.MemberMissionRepository;
import com.umc.prac.domain.member.repository.MemberRepository;
import com.umc.prac.domain.store.dto.response.MemberMissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MemberMissionQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberMissionQueryService(MemberRepository memberRepository, MemberMissionRepository memberMissionRepository) {
        this.memberRepository = memberRepository;
        this.memberMissionRepository = memberMissionRepository;
    }

    // 페이징으로 특정 회원의 진행중 미션 목록 조회
    public Page<MemberMissionResponse> getOngoingMissionsByMemberId(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."));

        Page<MemberMission> memberMissions = memberMissionRepository.findByMemberAndIsCompleteFalse(member, pageable);
        return memberMissions.map(mm -> new MemberMissionResponse(mm.getMemberMissionId(), mm.getMember().getMemberId(), mm.getMission().getMissionId(), mm.getIsComplete()));
    }
}

