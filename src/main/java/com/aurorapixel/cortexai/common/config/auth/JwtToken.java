package com.aurorapixel.cortexai.common.config.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtToken {
    @Value("${cortex.security.secret}")
    private String secret;
    @Value("${cortex.security.expiration}")
    private String expiration;

    public String generateToken(Object obj) {
//        String token = Jwts.builder()
//                .setSubject(obj.toString())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//
//        // 存储 token 到 Redis
//        redisTemplate.opsForValue().set(username, token, EXPIRATION_TIME, TimeUnit.MILLISECONDS);
//        return token;
        return null;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
//            String username = getUsernameFromToken(token);
//            String redisToken = redisTemplate.opsForValue().get(username);
//            return token.equals(redisToken) && !isTokenExpired(token);
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

}
