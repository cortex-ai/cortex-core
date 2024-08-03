package com.aurorapixel.cortexai.domain.repository.ai;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepository;
import com.aurorapixel.cortexai.domain.entity.ai.AIConversationEntity;

public interface AIConversationRepository extends BaseRepository<AIConversationEntity> {
    AIConversationEntity getConversationEntityByUUID(String conversationUUID);
}
