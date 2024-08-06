package com.aurorapixel.cortexai.infrastructure.repository.system;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepositoryImpl;
import com.aurorapixel.cortexai.domain.entity.system.SysSocialUserBindEntity;
import com.aurorapixel.cortexai.domain.repository.system.SysSocialUserBindRepository;
import com.aurorapixel.cortexai.infrastructure.mapper.system.SysSocialUserBindMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SysSocialUserBindRepositoryImpl extends BaseRepositoryImpl<SysSocialUserBindMapper, SysSocialUserBindEntity> implements SysSocialUserBindRepository {
}
