package com.project.banque.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JwtService {
    private final String secret = "CHANGE_ME_SECRET_KEY_very_secure";

    public String generateToken(UserDetails u) {
        // 1h
        long expireMs = 3600000;
        return Jwts.builder()
                .setSubject(u.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireMs))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean isTokenValid(String token, UserDetails u) {
        return extractUsername(token).equals(u.getUsername())
                && !Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration().before(new Date());
    }
}

