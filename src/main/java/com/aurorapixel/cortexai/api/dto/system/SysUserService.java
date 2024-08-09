package com.aurorapixel.cortexai.api.dto.system;

import com.aurorapixel.cortexai.common.exception.ServiceException;
import com.aurorapixel.cortexai.domain.entity.system.SysUserEntity;
import com.aurorapixel.cortexai.domain.repository.system.SysUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SysUserService {
    private final SysUserRepository sysUserRepository;
    public SysUserEntity findUserByAccountAndEmail(String account) {
        Optional<SysUserEntity> userEntityOptional = sysUserRepository.findByAccount(account);
        return userEntityOptional.orElseGet(() -> sysUserRepository.findByEmail(account)
                .orElseThrow(() -> new ServiceException(HttpStatus.UNAUTHORIZED.value(), "用户不存在")));
    }
}
