package com.aurorapixel.cortexai.common.config.auth;

import com.aurorapixel.cortexai.common.config.redis.RedisComponent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtToken {
    @Getter
    @Value("${cortex.security.expiration}")
    private Long expiration;
    @Autowired
    private RedisComponent redisComponent;

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    /**
     * token 生成
     * @param userDetails 用户信息
     * @return 生成的token
     */
    public String generateAccessToken(SecurityUserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userDetails.getUserId());
        claims.put("account",userDetails.getAccount());
        claims.put("name",userDetails.getName());
        claims.put("sex",userDetails.getSex());
        claims.put("email",userDetails.getEmail());
        claims.put("phone",userDetails.getPhone());
        claims.put("status",userDetails.getStatus());
        String token = createToken(claims, userDetails.getAccount(), expiration);
        //存储redis中
        redisComponent.setExpire("access_token:" + userDetails.getAccount(), token, expiration, TimeUnit.SECONDS);
        return token;
    }

    public String generateRefreshToken(SecurityUserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userDetails.getUserId());
        claims.put("account",userDetails.getAccount());
        String refreshToken = createToken(claims, userDetails.getAccount(), expiration * 24 * 7);
        //存储redis中
        redisComponent.setExpire("refresh_token:" + userDetails.getAccount(), refreshToken, expiration * 24 * 7, TimeUnit.SECONDS);
        return refreshToken;
    }


    /**
     * token生成器
     * @param claims 令牌
     * @param subject 用户
     * @return 生成的token
     */
    private String createToken(Map<String,Object> claims, String subject,Long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration*1000))
                .signWith(SECRET_KEY)
                .compact();
    }


    /**
     * 从token中获取用户名
     * @param token token
     * @return 用户名
     */
    public String getUserNameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    /**
     * 从token中获取令牌
     * @param token token
     * @return 令牌
     */
    public Map<String,Object> getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 校验令牌是否有效
     * @param token token
     * @return 是否有效
     */
    public boolean validateAccessToken(String token) {
        try {
            String username = getUserNameFromToken(token);
            String redisToken = redisComponent.get(getAccessTokenRedisKey(username),String.class);
            return redisToken.equals(token)&& !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            String username = getUserNameFromToken(token);
            String redisToken = redisComponent.get(getRefreshTokenRedisKey(username),String.class);
            return redisToken.equals(token)&& !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

    private String getAccessTokenRedisKey(String username){
        return "access_token:" + username;
    }

    private String getRefreshTokenRedisKey(String username){
        return "refresh_token:" + username;
    }



}
