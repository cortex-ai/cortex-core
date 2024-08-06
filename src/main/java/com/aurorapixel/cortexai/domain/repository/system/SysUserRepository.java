package com.aurorapixel.cortexai.domain.repository.system;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepository;
import com.aurorapixel.cortexai.domain.entity.system.SysUserEntity;

import java.util.Optional;

public interface SysUserRepository extends BaseRepository<SysUserEntity> {
    Optional<SysUserEntity> findByAccount(String account);
}
