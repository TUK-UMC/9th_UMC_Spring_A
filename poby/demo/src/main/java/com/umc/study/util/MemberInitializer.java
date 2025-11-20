package com.umc.study.util;

import com.umc.study.api.mypage.repository.MemberRepository;
import com.umc.study.domain.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MemberInitializer implements ApplicationRunner {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Override
    public void run(ApplicationArguments args) {
        if (memberRepository.count() == 0) {
            Member user1 = Member.builder()
                    .name("김철수")
                    .gender(Member.Gender.MALE)
                    .email("chulsoo@example.com")
                    .phone("010-1234-5678")
                    .detailAddress("서울시 강남구 테헤란로 123")
                    .socialType(Member.SocialType.KAKAO)
                    .socialUid("kakao_123456")
                    .phoneAuth(true)
                    .myPoint(1000)
                    .build();
            
            Member user2 = Member.builder()
                    .name("이영희")
                    .gender(Member.Gender.FEMALE)
                    .email("younghee@example.com")
                    .phone("010-9876-5432")
                    .detailAddress("부산시 해운대구 센텀로 456")
                    .socialType(Member.SocialType.NAVER)
                    .socialUid("naver_789012")
                    .phoneAuth(true)
                    .myPoint(2000)
                    .build();
            
            memberRepository.saveAll(Arrays.asList(user1, user2));
        }
    }
}