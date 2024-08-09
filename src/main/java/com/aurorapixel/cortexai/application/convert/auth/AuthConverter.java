package com.aurorapixel.cortexai.application.convert.auth;

import com.aurorapixel.cortexai.common.config.auth.SecurityUserDetails;
import com.aurorapixel.cortexai.domain.entity.system.SysUserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AuthConverter {

    public SecurityUserDetails convertSecurityUserDetails(SysUserEntity user) {
        List<SimpleGrantedAuthority> roleUser = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new SecurityUserDetails(user.getId(), user.getAccount(), user.getPassword(), user.getName(), user.getSex(), user.getEmail(), user.getPhone(), user.getStatus(),roleUser);
    }
}
