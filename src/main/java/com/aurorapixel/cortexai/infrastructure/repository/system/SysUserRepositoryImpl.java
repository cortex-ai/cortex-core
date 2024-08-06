package com.aurorapixel.cortexai.infrastructure.repository.system;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepositoryImpl;
import com.aurorapixel.cortexai.domain.entity.system.SysUserEntity;
import com.aurorapixel.cortexai.domain.repository.system.SysUserRepository;
import com.aurorapixel.cortexai.infrastructure.mapper.system.SysUserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SysUserRepositoryImpl extends BaseRepositoryImpl<SysUserMapper, SysUserEntity> implements SysUserRepository {
}
