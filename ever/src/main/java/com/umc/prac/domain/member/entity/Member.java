package com.umc.prac.domain.member.entity;

import com.umc.prac.domain.member.enumtype.Gender;
import com.umc.prac.domain.member.enumtype.Role;
import com.umc.prac.domain.member.enumtype.SocialType;
import com.umc.prac.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender; // MALE, FEMALE, NONE

    private String address;
    private String detailAddress;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, APPLE, GOOGLE, OAUTH

    private String socialEmail;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFood> memberFoods = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}