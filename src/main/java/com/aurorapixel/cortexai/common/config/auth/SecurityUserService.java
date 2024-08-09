package com.aurorapixel.cortexai.common.config.auth;

import com.aurorapixel.cortexai.common.exception.ServiceException;
import com.aurorapixel.cortexai.domain.entity.system.SysUserEntity;
import com.aurorapixel.cortexai.domain.repository.system.SysUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityUserService implements UserDetailsService {
    private final SysUserRepository sysUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SysUserEntity> userOptional = sysUserRepository.findByAccount(username);
        SysUserEntity sysUser = userOptional.orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST.value(),"用户名或密码错误, 请重新输入"));
        List<SimpleGrantedAuthority> roleUser = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new SecurityUserDetails(sysUser.getId(),sysUser.getAccount(),sysUser.getPassword(),sysUser.getName(),
                sysUser.getSex(),sysUser.getEmail(),sysUser.getPhone(),sysUser.getStatus(), roleUser);
    }
}
