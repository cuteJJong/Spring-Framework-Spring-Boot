package com.example.miniminitest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "your_jwt_secret_key_your_jwt_secret_key"; // 최소 32바이트 이상이어야 합니다.
    private final long EXPIRATION_TIME = 86400000; // 1일 (밀리초 단위)

    // 비밀 키 생성
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 토큰 생성
    public String generateToken(String loginId) {
        return Jwts.builder()
                .setSubject(loginId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰에서 클레임 추출
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰에서 로그인 ID 추출
    public String extractLoginId(String token) {
        return extractClaims(token).getSubject();
    }

    // 토큰 유효성 검사
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
