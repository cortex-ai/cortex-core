package com.aurorapixel.cortexai.api.controller.auth;


import com.aurorapixel.cortexai.annotation.CortexAIController;
import com.aurorapixel.cortexai.annotation.NoAuth;
import com.aurorapixel.cortexai.api.dto.auth.LoginDTO;
import com.aurorapixel.cortexai.api.response.R;
import com.aurorapixel.cortexai.api.response.auth.LoginResponse;
import com.aurorapixel.cortexai.application.service.auth.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 授登陆
 */
@CortexAIController("/auth")
@AllArgsConstructor
@NoAuth
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public R<LoginResponse> login(@RequestBody @Valid LoginDTO loginDTO) {
        return R.ok(authService.login(loginDTO));
    }

    @GetMapping("/refreshToken")
    public R<LoginResponse> refreshToken(@Valid @NotNull String refreshToken) {
        return R.ok(authService.refreshToken(refreshToken));
    }


    @GetMapping("/ja3")
    public R<String> getJa3(HttpServletRequest request) {
        return R.ok(request.getHeader("X-JA3-Fingerprint"));
    }
}
