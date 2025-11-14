package com.example.umc9th.domain.food.entity;

import com.example.umc9th.domain.food.enums.FoodName;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Food extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FoodName name;

    @Builder.Default
    @OneToMany(mappedBy = "food", fetch = FetchType.LAZY)
    private List<MemberFood> memberFoods = new ArrayList<>();
}
