package com.umc.study.api.mypage.service;

import com.umc.study.api.mypage.dto.MyPageResponse;
import com.umc.study.api.mypage.repository.MemberRepository;
import com.umc.study.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

  private final MemberRepository memberRepository;

  public MyPageResponse getMyPage(Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

    return MyPageResponse.from(member);
  }
}