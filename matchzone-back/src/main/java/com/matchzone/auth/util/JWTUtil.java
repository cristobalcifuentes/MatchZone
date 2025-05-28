package com.matchzone.auth.util;


import com.matchzone.common.model.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtil {

    private final Key key;
    private final long jwtExpirationMs;
    private final long emailVerificationExpirationMs;

    public JWTUtil(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.expiration}") long jwtExpirationMs,
        @Value("${jwt.email-verification-expiration}") long emailVerificationExpirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationMs = jwtExpirationMs;
        this.emailVerificationExpirationMs = emailVerificationExpirationMs;
    }

    // TOKEN NORMAL (AUTENTICACIÓN)
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // TOKEN PARA VERIFICACIÓN DE EMAIL
    public String generateEmailVerificationToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + emailVerificationExpirationMs))
                .claim("purpose", "email_verification")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmailFromVerificationToken(String token) {
        Claims claims = extractAllClaims(token);
        if (!"email_verification".equals(claims.get("purpose"))) {
            throw new IllegalArgumentException("Token inválido para verificación de email");
        }
        return claims.getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }
    
    public String generatePasswordResetToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 min
                .claim("purpose", "password_reset")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}