package com.aurorapixel.cortexai.common.utils;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

public class AuthUtil {
    private AuthUtil() {
        throw new UnsupportedOperationException("这是一个工具类，不能实例化。");
    }
    private static final String AUTHORIZATION = "Authorization";

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        return null;
    }

    public static Long getIp() {
        //未登录用户默认-1
        return -1L;
    }


    /**
     * 获取Claims
     *
     * @param request request
     * @return Claims
     */
    public static Claims getClaims(HttpServletRequest request) {
//        String token = request.getHeader(AUTHORIZATION);
//        if (StrUtil.isEmpty(auth)) {
//            return null;
//        }
//        Claims claims = null;
//        String token = null;
//        // 获取 请求头 参数
//        if (StrUtil.isNotBlank(auth)) {
//            token = JWTUtil.getToken(auth);
//        }
//        // 获取 Token 值
//        if (StrUtil.isNotBlank(token)) {
//            claims = JWTUtil.parseJWT(token);
//        }
//        // 判断 Token 状态
//        if (claims != null) {
//            String userIdStr = String.valueOf(claims.get(USER_ID));
//            Long userId = Long.valueOf(userIdStr);
//            String accessToken = JWTUtil.getAccessToken(userId, userType);
//            if (!token.equals(accessToken)) {
//                return null;
//            }
//        }
//        return claims;
        return null;
    }
}
