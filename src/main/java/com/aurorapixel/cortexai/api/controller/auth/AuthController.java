package com.aurorapixel.cortexai.api.controller.auth;


import com.aurorapixel.cortexai.annotation.CortexAIController;
import com.aurorapixel.cortexai.api.dto.auth.LoginDTO;
import com.aurorapixel.cortexai.api.response.R;
import com.aurorapixel.cortexai.api.response.auth.LoginResponse;
import com.aurorapixel.cortexai.application.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 授登陆
 */
@CortexAIController("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public R<LoginResponse> login(@RequestBody @Valid LoginDTO loginDTO) {
        return R.ok(authService.login(loginDTO));
    }
}
