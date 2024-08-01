package com.aurorapixel.cortexai.domain.repository.ai;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepository;
import com.aurorapixel.cortexai.domain.entity.ai.AIModelConfigEntity;

import java.util.Optional;

/**
 * 领域层（Domain）：包含领域模型和业务规则，定义实体、值对象、聚合、仓库接口等。
 * AI模型配置仓库接口
 */
public interface AIModelConfigRepository extends BaseRepository<AIModelConfigEntity> {
    /**
     * 根据用户ID和模型名称查找模型配置
     * @param userId  用户ID
     * @param modelName 模型名称
     * @return 模型配置
     */
    Optional<AIModelConfigEntity> findByUserIdAndModelName(Long userId, String modelName);

    /**
     * 根据模型名称查找系统默认数据
     * @param modelName 模型名称
     * @return 模型配置
     */
    Optional<AIModelConfigEntity> findSystemDefaultByModelName(String modelName);
}
