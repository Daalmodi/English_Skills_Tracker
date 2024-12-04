package com.example.EnglishSkillTrackerCRUD.config;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
@Component
public class JwtEncoder {
    private static final String SECRET_KEY="my-secret-key-es-loSuFICIENTEMENTE-LARGA";
    public String generateToken(Map<String,Object> claims){
        return Jwts.builder()
               .claims(claims)
               .issuedAt(new Date(System.currentTimeMillis()))
               .expiration(new Date(System.currentTimeMillis()+1000 *60 *60 *10))
               .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
               .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().parseSignedClaims(token);
            return true; 
        } catch (Exception e) {
            return false;
            
        }
    }

    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().parseSignedClaims(token).getPayload();
    }
}
