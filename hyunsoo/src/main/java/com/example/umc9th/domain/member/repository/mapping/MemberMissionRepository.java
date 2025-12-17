package com.example.umc9th.domain.member.repository.mapping;

import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Value;

//MemberMission 엔티티용 리포지토리
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {


    interface MyMissionView {
        // 성공여부: MemberMission.isComplete
        Boolean getIsComplete();

        // 포인트: Mission.point
        @Value("#{target.mission.point}")
        Integer getPoint();

        // 미션명(조건): Mission.condition
        @Value("#{target.mission.condition}")
        String getCondition();

        // 가게 이름: Mission.store.name
        @Value("#{target.mission.store.name}")
        String getStoreName();
    }

    // 최신순 페이징
    Page<MyMissionView> findByMember_IdOrderByCreatedAtDesc(Long memberId, Pageable pageable);

    // 이미 도전중인 미션인지 확인
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    // 진행중인 미션 목록 조회 (isComplete = false)
    Page<MemberMission> findByMemberIdAndIsCompleteFalseOrderByCreatedAtDesc(Long memberId, Pageable pageable);
}
