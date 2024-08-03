package com.aurorapixel.cortexai.domain.repository.ai;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepository;
import com.aurorapixel.cortexai.domain.entity.ai.AIConversationMessageEntity;

import java.util.List;

public interface AIConversationMessageRepository extends BaseRepository<AIConversationMessageEntity> {
    List<AIConversationMessageEntity> getListByConversationId(Long conversationId, Integer maxIndex);
}
