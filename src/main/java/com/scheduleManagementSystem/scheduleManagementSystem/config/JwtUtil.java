package com.scheduleManagementSystem.scheduleManagementSystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "sadgasdfgdfassodkfgoksgokdfbvoiertjvboidfvoierjgifohdtrfyjvdytehjyhgfbghtyjujkhjm";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours


    //Generate token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    //Get username from token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Boolean isValid(String token) {
        try {
            return !extractClaims(token).getExpiration().before(new Date());
        }catch (Exception e) {
            return false;
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
