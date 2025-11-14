package com.umc.study.domain.food.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private FoodCategory name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoods = new ArrayList<>();

    public enum FoodCategory {
        KOREAN, JAPANESE, CHINESE, WESTERN, DESSERT, CHICKEN, PIZZA, FASTFOOD, CAFE, BAR, ETC
    }
}