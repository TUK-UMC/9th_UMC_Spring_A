package com.umc.prac.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review_photo")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ReviewPhoto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPhotoId;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}
