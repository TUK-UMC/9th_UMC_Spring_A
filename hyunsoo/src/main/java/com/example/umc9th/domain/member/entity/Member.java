package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.entity.mapping.MemberTerm;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.auth.enums.SocialType;
import com.example.umc9th.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;  // nullable 허용

    private LocalDate birth;

    // 상세주소(문자열)
    private String detailAddress;  // nullable 허용

    // 광역주소(열거형)
    @Enumerated(EnumType.STRING)
    private com.example.umc9th.domain.store.enums.Address address;  // nullable 허용

    @Enumerated(EnumType.STRING)
    private SocialType socialType;  // nullable 허용

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    /* 연관관계(양방향 컬렉션) */
    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFood> memberFoods = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();
}
