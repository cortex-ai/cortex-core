package com.aurorapixel.cortexai.infrastructure.repository.ai;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepositoryImpl;
import com.aurorapixel.cortexai.domain.entity.ai.AIConversationMessageEntity;
import com.aurorapixel.cortexai.domain.repository.ai.AIConversationMessageRepository;
import com.aurorapixel.cortexai.infrastructure.mapper.AIConversationMessageMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AIConversationMessageRepositoryImpl extends BaseRepositoryImpl<AIConversationMessageMapper, AIConversationMessageEntity> implements AIConversationMessageRepository {
    @Override
    public List<AIConversationMessageEntity> getListByConversationId(Long conversationId, Integer maxIndex) {
        LambdaQueryWrapper<AIConversationMessageEntity> queryWrapper = new LambdaQueryWrapper<AIConversationMessageEntity>().
                eq(AIConversationMessageEntity::getConversationId, conversationId)
                .orderByDesc(AIConversationMessageEntity::getCreateTime)
                .last("limit " + maxIndex);
        return list(queryWrapper);
    }
}
