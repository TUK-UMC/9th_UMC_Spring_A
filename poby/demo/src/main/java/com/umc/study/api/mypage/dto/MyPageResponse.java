package com.umc.study.api.mypage.dto;

import com.umc.study.domain.member.entity.Member;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageResponse {
  private String name;
  private String email;
  private String phoneNumber;
  private String profileImageUrl;
  private Integer myPoint;
  private Boolean phoneAuth;

  public static MyPageResponse from(Member member) {
    return MyPageResponse.builder()
        .name(member.getName())
        .email(member.getEmail())
        .phoneNumber(member.getPhone())
        .profileImageUrl(member.getProfileImageUrl())
        .myPoint(member.getMyPoint())
        .phoneAuth(member.isPhoneAuth())
        .build();
  }
}
