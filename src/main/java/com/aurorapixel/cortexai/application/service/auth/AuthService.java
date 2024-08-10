package com.aurorapixel.cortexai.application.service.auth;

import com.aurorapixel.cortexai.api.dto.auth.LoginDTO;
import com.aurorapixel.cortexai.api.dto.system.SysUserService;
import com.aurorapixel.cortexai.api.response.auth.LoginResponse;
import com.aurorapixel.cortexai.application.convert.auth.AuthConverter;
import com.aurorapixel.cortexai.common.config.auth.JwtToken;
import com.aurorapixel.cortexai.common.config.auth.SecurityUserDetails;
import com.aurorapixel.cortexai.common.exception.ServiceException;
import com.aurorapixel.cortexai.domain.entity.system.SysUserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final SysUserService sysUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final AuthConverter authConverter;


    /**
     * 登录
     * @param loginDTO 登录实体
     * @return 登录响应
     */
    public LoginResponse login(LoginDTO loginDTO) {
        //获取用户
        SysUserEntity user = sysUserService.findUserByAccountAndEmail(loginDTO.getAccount());
        //密码校验
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount(), loginDTO.getPassword());
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value(),"用户名或密码错误");
        }
        SecurityUserDetails securityUserDetails = authConverter.convertSecurityUserDetails(user);
        String accessToken = jwtToken.generateAccessToken(securityUserDetails);
        String refreshToken = jwtToken.generateRefreshToken(securityUserDetails);
        return new LoginResponse(accessToken, refreshToken, jwtToken.getExpiration());
    }

    public LoginResponse refreshToken(String refreshToken) {
       if (!jwtToken.validateRefreshToken(refreshToken)) {
           throw new ServiceException(HttpStatus.UNAUTHORIZED.value(),"令牌已过期");
       }
       String username = jwtToken.getUserNameFromToken(refreshToken);
       SysUserEntity user = sysUserService.findUserByAccountAndEmail(username);
       SecurityUserDetails securityUserDetails = authConverter.convertSecurityUserDetails(user);
       String accessToken = jwtToken.generateAccessToken(securityUserDetails);
       return new LoginResponse(accessToken, refreshToken, jwtToken.getExpiration());
    }
}
