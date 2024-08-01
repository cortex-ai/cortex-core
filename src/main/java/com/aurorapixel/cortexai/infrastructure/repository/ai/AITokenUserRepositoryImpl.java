package com.aurorapixel.cortexai.infrastructure.repository.ai;


import com.aurorapixel.cortexai.common.config.mybatis.BaseRepositoryImpl;
import com.aurorapixel.cortexai.domain.entity.ai.AITokenUseEntity;
import com.aurorapixel.cortexai.domain.repository.ai.AITokenUserRepository;
import com.aurorapixel.cortexai.infrastructure.repository.ai.mapper.AITokenUserMapper;
import org.springframework.stereotype.Repository;


@Repository
public class AITokenUserRepositoryImpl extends BaseRepositoryImpl<AITokenUserMapper, AITokenUseEntity> implements AITokenUserRepository {
}
