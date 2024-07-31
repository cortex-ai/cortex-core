package com.aurorapixel.cortexai.infrastructure.repository.ai;

import com.aurorapixel.cortexai.domain.entity.ai.AIModelConfigEntity;
import com.aurorapixel.cortexai.domain.repository.ai.AIModelConfigRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AIModelConfigRepository implements AIModelConfigRepositoryInterface {

    /**
     * 根据用户ID和模型名称查找模型配置
     * @param userId  用户ID
     * @param modelName 模型名称
     * @return 模型配置
     */
    @Override
    public Optional<AIModelConfigEntity> findByUserIdAndModelName(Long userId, String modelName) {
        return Optional.empty();
    }


    /**
     * 根据模型名称查找系统默认数据
     * @param modelName 模型名称
     * @return 模型配置
     */
    @Override
    public Optional<AIModelConfigEntity> findSystemDefaultByModelName(String modelName) {
        return Optional.empty();
    }
}
