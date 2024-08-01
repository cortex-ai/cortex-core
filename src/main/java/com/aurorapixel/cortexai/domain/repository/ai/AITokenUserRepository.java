package com.aurorapixel.cortexai.domain.repository.ai;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepository;
import com.aurorapixel.cortexai.domain.entity.ai.AITokenUseEntity;

/**
 * 领域层（Domain）：包含领域模型和业务规则，定义实体、值对象、聚合、仓库接口等。
 * AI 用户Token仓库
 */
public interface AITokenUserRepository extends BaseRepository<AITokenUseEntity> {
}
