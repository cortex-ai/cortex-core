package com.aurorapixel.cortexai.infrastructure.repository.ai;

import com.aurorapixel.cortexai.common.config.mybatis.BaseRepositoryImpl;
import com.aurorapixel.cortexai.domain.entity.ai.AIConversationMessageEntity;
import com.aurorapixel.cortexai.domain.repository.ai.AIConversationMessageRepository;
import com.aurorapixel.cortexai.infrastructure.mapper.AIConversationMessageMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AIConversationMessageRepositoryImpl extends BaseRepositoryImpl<AIConversationMessageMapper, AIConversationMessageEntity> implements AIConversationMessageRepository {
}
