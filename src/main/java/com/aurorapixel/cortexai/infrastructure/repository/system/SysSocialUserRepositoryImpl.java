package com.aurorapixel.cortexai.infrastructure.repository.system;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepositoryImpl;
import com.aurorapixel.cortexai.domain.entity.system.SysSocialUserEntity;
import com.aurorapixel.cortexai.domain.repository.system.SysSocialUserRepository;
import com.aurorapixel.cortexai.infrastructure.mapper.system.SysSocialUserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SysSocialUserRepositoryImpl extends BaseRepositoryImpl<SysSocialUserMapper, SysSocialUserEntity> implements SysSocialUserRepository {
}
