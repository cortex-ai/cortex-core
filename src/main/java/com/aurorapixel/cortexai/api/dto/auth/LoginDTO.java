package com.aurorapixel.cortexai.api.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
    /**
     * 账号：手机号/邮箱
     */
    @NotNull
    private String account;
    /**
     * 密码
     */
    @NotNull
    private String password;
}
