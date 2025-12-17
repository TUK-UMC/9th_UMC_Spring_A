package com.umc.prac.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long accessTokenValidityMs = 1000L * 60 * 60; // 1시간

    public String generateAccessToken(Long memberId, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessTokenValidityMs);

        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


