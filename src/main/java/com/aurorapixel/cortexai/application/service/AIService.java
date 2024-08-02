package com.aurorapixel.cortexai.application.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.aurorapixel.cortexai.api.dto.ChatMessageDTO;
import com.aurorapixel.cortexai.application.factory.AIProviderFactory;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.output.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AIService {
    private AIProviderFactory aiProviderFactory;
    /**
     * stream方式发送消息
     * @param chatMessageDTO 消息请求体
     * @param sseEmitter SseEmitter
     */
    public void streamChat(ChatMessageDTO chatMessageDTO, SseEmitter sseEmitter) {
        List<ChatMessage> chatMessages = new ArrayList<>();

        //1.判断是否开启新的会话
        String conversationId = chatMessageDTO.getConversationId();
        Boolean isNewConversation = Objects.isNull(conversationId);
        if(StrUtil.isEmpty(conversationId)){
            conversationId = UUID.fastUUID().toString();
            //TODO: 保存会话
        }
        //2.获取聊天记录
        if(isNewConversation&&chatMessageDTO.getIsContext()){
            //TODO: 获取历史记录
        }
        //3.保存用户问题 TODO

        //4.获取聊天模型
        StreamingChatLanguageModel streamingChatLanguageModel = aiProviderFactory.getStreamingChatLanguageModel(chatMessageDTO.getModelName());

        //5.发送消息
        chatMessages.add(new UserMessage(chatMessageDTO.getContent()));
        streamingChatLanguageModel.generate(chatMessages, new StreamingResponseHandler<AiMessage>() {

            @Override
            public void onNext(String token) {

            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                StreamingResponseHandler.super.onComplete(response);
            }

            @Override
            public void onError(Throwable error) {

            }
        });



    }
}
