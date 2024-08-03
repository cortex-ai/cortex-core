package com.aurorapixel.cortexai.application.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.aurorapixel.cortexai.api.dto.ai.AIChatMessageDTO;
import com.aurorapixel.cortexai.api.response.ai.AIChatResponse;
import com.aurorapixel.cortexai.application.factory.AIProviderFactory;
import com.aurorapixel.cortexai.common.utils.AIUtil;
import com.aurorapixel.cortexai.common.utils.AuthUtil;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.output.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class AIChatService {
    private AIProviderFactory providerFactory;
    private AIConversationService conversationService;
    private AIConversationMessageService conversationMessageService;
    /**
     * stream方式发送消息
     * @param AIChatMessageDTO 消息请求体
     * @param sseEmitter SseEmitter
     */
    @Transactional
    public void streamChat(AIChatMessageDTO AIChatMessageDTO, SseEmitter sseEmitter) {
        List<ChatMessage> chatMessages = new ArrayList<>();

        //1.判断是否开启新的会话
        String conversationUUID = AIChatMessageDTO.getConversationUUID();
        String content = AIChatMessageDTO.getContent();
        boolean isNewConversation = Objects.isNull(conversationUUID);
        Long conversationId;
        if(StrUtil.isEmpty(conversationUUID)){
            conversationUUID = UUID.fastUUID().toString();
            //生成会话标题
            String conversationSummary = getConversationSummary(content);
            conversationId = conversationService.addConversation(conversationUUID, conversationSummary);
        }else{
            //查找会话是否存在
            conversationId = conversationService.getConversationId(conversationUUID);
        }

        //2.获取聊天记录
        if(!isNewConversation&& AIChatMessageDTO.getIsContext()){
            List<ChatMessage> historyChatMessage = conversationMessageService.getHistoryChatMessages(conversationId, AIChatMessageDTO.getMaxIndex());
            chatMessages.addAll(historyChatMessage);
        }

        //3.保存用户问题
        conversationMessageService.addUserMessage(conversationId, content);

        //4.获取聊天模型
        //StreamingChatLanguageModel streamingChatLanguageModel = providerFactory.getStreamingChatLanguageModel(AIChatMessageDTO.getModelName());
        StreamingChatLanguageModel streamingChatLanguageModel = providerFactory.getFreeStreamingChatLanguageModel();

        //5.发送消息
        chatMessages.add(new UserMessage(content));
        AIChatResponse outPut = new AIChatResponse();
        outPut.setConversationUUID(conversationUUID);
        Long userId = AuthUtil.getUserId();
        streamingChatLanguageModel.generate(chatMessages, new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                outPut.setContent(token);
                try {
                    sseEmitter.send(SseEmitter.event().name("message").data(outPut));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                //1.保存聊天记录
                conversationMessageService.addAIChatMessage(userId,conversationId, response.content().text());

                outPut.setIsFinish(true);
                outPut.setFinishReason(response.finishReason().name());
                outPut.setContent(response.content().text());
                try {
                    sseEmitter.send(SseEmitter.event().name("message").data(outPut));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sseEmitter.complete();
            }

            @Override
            public void onError(Throwable error) {
                outPut.setContent(error.getMessage());
                outPut.setIsError(true);
                outPut.setIsFinish(true);
                outPut.setFinishReason("error");
                try {
                    sseEmitter.send(SseEmitter.event().name("message").data(outPut));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sseEmitter.completeWithError(error);
            }
        });



    }


    /**
     * 获取会话概要
     * @return 会话概要
     */
    public String getConversationSummary(String content) {
        String promptTemplate = AIUtil.loadPrompt("prompt/ConversationSummaryPrompt.txt");
        String prompt = promptTemplate.replace("{{input}}", content);
        log.info("开始生成会话摘要:{}",content);
        ChatLanguageModel freeChatLanguageModel = providerFactory.getFreeChatLanguageModel();
        return freeChatLanguageModel.generate(prompt);
    }
}
