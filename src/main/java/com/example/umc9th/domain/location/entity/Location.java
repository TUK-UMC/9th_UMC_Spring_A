package com.example.umc9th.domain.location.entity;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Location extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String name;

    @Builder.Default
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Store> stores = new ArrayList<>();
}
