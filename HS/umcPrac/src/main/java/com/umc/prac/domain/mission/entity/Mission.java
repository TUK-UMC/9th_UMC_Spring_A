package com.umc.prac.domain.mission.entity;

import com.umc.prac.domain.member.entity.MemberMission;
import com.umc.prac.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Mission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    private LocalDate deadline;

    @Column(length = 255)
    private String conditional;

    private Integer point;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>();
}
