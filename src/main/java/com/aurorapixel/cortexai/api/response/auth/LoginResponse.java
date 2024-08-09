package com.aurorapixel.cortexai.api.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    /**
     * token
     */
    private String token;
    /**
     * 刷新token
     */
    private String refreshToken;
    /**
     * 过期时间
     */
    private Long expiresTime;
}
