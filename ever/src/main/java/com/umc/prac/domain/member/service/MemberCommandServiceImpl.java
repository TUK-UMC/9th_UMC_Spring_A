package com.umc.prac.domain.member.service;

import com.umc.prac.domain.member.converter.MemberConverter;
import com.umc.prac.domain.member.dto.*;
import com.umc.prac.domain.member.entity.Member;
import com.umc.prac.domain.member.enumtype.Role;
import com.umc.prac.domain.member.repository.MemberRepository;
import com.umc.prac.global.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberCommandServiceImpl(MemberRepository memberRepository,
                                    PasswordEncoder passwordEncoder,
                                    JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public MemberJoinResponse join(MemberJoinRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 가입된 이메일입니다.");
    }

        Member member = Member.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_USER)
                .build();

        Member saved = memberRepository.save(member);
        return MemberConverter.toJoinDTO(saved);
    }

    @Override
    public SessionLoginResponse sessionLogin(LoginRequest request, HttpServletRequest httpRequest) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("LOGIN_MEMBER", member.getMemberId());

        return MemberConverter.toSessionLoginDTO(member);
    }

    @Override
    public JwtLoginResponse jwtLogin(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(member.getMemberId(), member.getRole().name());
        return new JwtLoginResponse(accessToken);
    }
}


