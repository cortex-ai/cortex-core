package com.aurorapixel.cortexai.application.convert.ai;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.aurorapixel.cortexai.common.constants.AIConstant;
import com.aurorapixel.cortexai.domain.entity.ai.AIConversationMessageEntity;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AIConversationMessageConverter {
    public List<ChatMessage> toChatMessages(List<AIConversationMessageEntity> conversationMessageEntities) {
        List<ChatMessage> result = new ArrayList<>();
        if(CollUtil.isEmpty(conversationMessageEntities)) {
            return result;
        }
        for (AIConversationMessageEntity conversationMessageEntity : conversationMessageEntities) {
            String roleType = conversationMessageEntity.getRoleType();
            if(StrUtil.isEmpty(roleType)) {
                continue;
            }
            if(Objects.equals(roleType, AIConstant.USER)) {
                result.add(new UserMessage(conversationMessageEntity.getContent()));
            }
            if(Objects.equals(roleType, AIConstant.SYSTEM)) {
                result.add(new SystemMessage(conversationMessageEntity.getContent()));
            }
            if(Objects.equals(roleType, AIConstant.AI)) {
                result.add(new AiMessage(conversationMessageEntity.getContent()));
            }
        }
        return result;
    }
}
