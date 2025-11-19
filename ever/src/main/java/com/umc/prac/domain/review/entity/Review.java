package com.umc.prac.domain.review.entity;

import com.umc.prac.domain.member.entity.Member;
import com.umc.prac.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Lob
    private String content;

    // 리뷰 별점 (예: 4.5)
    private Double star;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhoto> reviewPhotos = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();
}
