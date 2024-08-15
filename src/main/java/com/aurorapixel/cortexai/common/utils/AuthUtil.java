package com.aurorapixel.cortexai.common.utils;

import com.aurorapixel.cortexai.common.config.auth.JwtToken;
import com.aurorapixel.cortexai.common.config.auth.SecurityUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class AuthUtil {
    private static JwtToken jwtToken;
    @Autowired
    public AuthUtil(JwtToken jwtToken){
        AuthUtil.jwtToken = jwtToken;
    }


    /**
     * 获取用户id
     */
    public static Long getUserId() {
        SecurityUserDetails user = getUser();
        if(Objects.nonNull(user)){
            return user.getUserId();
        }
        return null;
    }

    public static Long getIp() {
        //未登录用户默认-1
        return -1L;
    }

    public static SecurityUserDetails getUser(){
        Map<String, Object> claims = getClaims();
        if(Objects.isNull(claims)){
            return null;
        }
        Object userIdObject = claims.get("userId");
        Long userId = null;
        if(userIdObject instanceof Long){
            userId = (Long) userIdObject;
        } else if (userIdObject instanceof Integer){
            userId = ((Integer) userIdObject).longValue();
        }

        String account = (String) claims.get("account");
        String name = (String) claims.get("name");
        Integer sex = (Integer) claims.get("sex");
        String email = (String) claims.get("email");
        String phone = (String) claims.get("phone");
        Integer status = (Integer) claims.get("status");

        List<SimpleGrantedAuthority> roleUser = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new SecurityUserDetails(userId,account,"",name,sex,email,phone,status,roleUser);
    }


    /**
     * 获取Claims
     *
     * @return Claims
     */
    public static Map<String, Object> getClaims() {
        String token = WebUtil.authorizationToken();
        return jwtToken.getClaimsFromToken(token);
    }
}
