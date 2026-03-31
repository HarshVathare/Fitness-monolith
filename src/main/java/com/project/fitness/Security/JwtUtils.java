package com.project.fitness.Security;

import com.project.fitness.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtUtils {

    private String jwtSecret = "ghjyfghtdsfrhghjuiojklghjukjdftuyjkijklofghuksdfxcvjkliuykh";

    private int JwtExpiration = 172800000; // 48 hrs

    public String getJwtFromHeaders(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId())
                .claim("roles", Collections.singletonList(user.getRoles().name()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtExpiration))
                .signWith(getSecretKey())
                .compact();
    }

    public Boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(jwtToken);
            return true;
        } catch (JwtException e) {
            return false; // ✅ do not throw
        }
    }

    private SecretKey getSecretKey() { // ✅ FIXED NAME
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String getUsernameFromToken(String jwt) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }

    public Claims getAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}