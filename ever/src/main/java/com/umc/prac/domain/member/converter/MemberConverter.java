package com.umc.prac.domain.member.converter;

import com.umc.prac.domain.member.dto.MemberJoinResponse;
import com.umc.prac.domain.member.dto.SessionLoginResponse;
import com.umc.prac.domain.member.entity.Member;

public final class MemberConverter {

    private MemberConverter() {
    }

    public static MemberJoinResponse toJoinDTO(Member member) {
        return new MemberJoinResponse(member.getMemberId(), member.getName(), member.getEmail());
    }

    public static SessionLoginResponse toSessionLoginDTO(Member member) {
        return new SessionLoginResponse(member.getMemberId(), member.getName(), member.getEmail());
    }
}


