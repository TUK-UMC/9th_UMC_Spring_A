package com.umc.study.domain.member.entity;

import com.umc.study.domain.food.entity.MemberFood;
import com.umc.study.domain.mission.entity.MemberMission;
import com.umc.study.domain.review.entity.Review;
import com.umc.study.domain.term.entity.MemberTerm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "detail_address")
    private String detailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @Column(name = "social_uid", length = 191)
    private String socialUid;

    @Column(length = 191)
    private String email;

    private boolean phoneAuth;

    @Column(length = 30)
    private String phone;

    @Column(name = "profile_image_url", length = 500)
    private String profileImageUrl;

    private Integer myPoint;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoods = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    public enum Gender {
        MALE, FEMALE, NONE
    }

    public enum SocialType {
        KAKAO, NAVER, APPLE, GOOGLE
    }
}