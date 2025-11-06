package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //마이페이지 화면 뷰
    interface MyPageView {
        String getEmail();
        String getPhoneNumber();
        Integer getPoint();   // 엔티티 타입에 맞춰 Integer/Long 등으로 맞추기
    }

    // PK로 조회 (마이페이지: 내 정보 한 건)
    Optional<MyPageView> findProjectedById(Long id);
}
