package com.aurorapixel.cortexai.infrastructure.repository.ai;


import com.aurorapixel.cortexai.common.config.mybatis.BaseRepositoryImpl;
import com.aurorapixel.cortexai.domain.entity.ai.AIConversationEntity;
import com.aurorapixel.cortexai.domain.repository.ai.AIConversationRepository;
import com.aurorapixel.cortexai.infrastructure.mapper.AIConversationMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Repository;

@Repository
public class AIConversationRepositoryImpl extends BaseRepositoryImpl<AIConversationMapper, AIConversationEntity> implements AIConversationRepository {
    @Override
    public AIConversationEntity getConversationEntityByUUID(String conversationUUID) {
        LambdaQueryWrapper<AIConversationEntity> queryWrapper = new LambdaQueryWrapper<AIConversationEntity>().eq(AIConversationEntity::getConversationUUID, conversationUUID);
        return this.getOne(queryWrapper);
    }
}
