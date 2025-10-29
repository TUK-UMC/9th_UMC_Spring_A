package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate deadline;

    @Column(length = 255)
    private String condition;   // 미션 조건

    private Integer point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Builder.Default
    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY)
    private List<MemberMission> memberMissions = new ArrayList<>();
}
