package com.umc.prac.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_food")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MemberFood {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberFoodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
