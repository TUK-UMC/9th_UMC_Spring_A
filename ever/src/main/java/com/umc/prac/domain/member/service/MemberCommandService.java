package com.umc.prac.domain.member.service;

import com.umc.prac.domain.member.dto.*;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberCommandService {

    MemberJoinResponse join(MemberJoinRequest request);

    SessionLoginResponse sessionLogin(LoginRequest request, HttpServletRequest httpRequest);

    JwtLoginResponse jwtLogin(LoginRequest request);
}


