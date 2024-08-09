package com.aurorapixel.cortexai.application.service.ai;

import com.aurorapixel.cortexai.application.convert.ai.AIConversationMessageConverter;
import com.aurorapixel.cortexai.common.constants.AIConstant;
import com.aurorapixel.cortexai.domain.entity.ai.AIConversationMessageEntity;
import com.aurorapixel.cortexai.domain.repository.ai.AIConversationMessageRepository;
import dev.langchain4j.data.message.ChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AIConversationMessageService {
    private final AIConversationMessageRepository aiConversationMessageRepository;
    private final AIConversationMessageConverter converter;

    public List<ChatMessage> getHistoryChatMessages(Long conversationId, Integer maxIndex) {
        List<AIConversationMessageEntity> conversationMessageEntities =  aiConversationMessageRepository.getListByConversationId(conversationId, maxIndex);
        conversationMessageEntities.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        return converter.toChatMessages(conversationMessageEntities);
    }

    public void addUserMessage(Long conversationId, String content) {
        AIConversationMessageEntity aiConversationMessageEntity = new AIConversationMessageEntity();
        aiConversationMessageEntity.setConversationId(conversationId);
        aiConversationMessageEntity.setContent(content);
        aiConversationMessageEntity.setRoleType(AIConstant.USER);
        aiConversationMessageEntity.setStatus(0);
        aiConversationMessageRepository.save(aiConversationMessageEntity);
    }

    public void addAIChatMessage(Long userId, Long conversationId, String text) {
        AIConversationMessageEntity aiConversationMessageEntity = new AIConversationMessageEntity();
        aiConversationMessageEntity.setConversationId(conversationId);
        aiConversationMessageEntity.setContent(text);
        aiConversationMessageEntity.setRoleType(AIConstant.AI);
        aiConversationMessageEntity.setStatus(0);
        aiConversationMessageRepository.save(aiConversationMessageEntity);
    }
}
