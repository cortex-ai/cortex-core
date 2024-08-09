package com.aurorapixel.cortexai.application.service.ai;

import com.aurorapixel.cortexai.common.exception.ServiceException;
import com.aurorapixel.cortexai.common.utils.AuthUtil;
import com.aurorapixel.cortexai.domain.entity.ai.AIConversationEntity;
import com.aurorapixel.cortexai.domain.repository.ai.AIConversationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AIConversationService {
    private final AIConversationRepository aiConversationRepository;


    /**
     * 添加会话
     * @param conversationUUID 会话UUID
     * @param conversationSummary 会话概要
     * @return 会话主键id
     */
    public Long addConversation(String conversationUUID, String conversationSummary) {
        AIConversationEntity aiConversationEntity = new AIConversationEntity();
        aiConversationEntity.setConversationUUID(conversationUUID);
        aiConversationEntity.setConversationSummary(conversationSummary);
        aiConversationEntity.setUserId(AuthUtil.getUserId());
        aiConversationRepository.save(aiConversationEntity);
        return aiConversationEntity.getId();
    }

    public Long getConversationId(String conversationUUID) {
        AIConversationEntity aiConversationEntity = aiConversationRepository.getConversationEntityByUUID(conversationUUID);
        if(Objects.isNull(aiConversationEntity)) {
            throw new ServiceException("会话不存在");
        }
        return aiConversationEntity.getId();
    }
}
