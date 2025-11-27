package com.umc.prac.domain.member.entity;

import com.umc.prac.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_mission")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MemberMission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMissionId;

    private Boolean isComplete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
}
