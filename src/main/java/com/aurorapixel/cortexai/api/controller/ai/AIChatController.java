package com.aurorapixel.cortexai.api.controller.ai;

import com.aurorapixel.cortexai.annotation.CortexAIController;
import com.aurorapixel.cortexai.api.dto.ChatMessageDTO;
import com.aurorapixel.cortexai.application.service.AIChatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * AI-chat应用
 */
@CortexAIController("/ai/chat")
@AllArgsConstructor
public class AIChatController {
    private AIChatService aiChatService;

    /**
     * stream方式发送消息
     * @param chatMessageDTO 消息请求体
     * @return SseEmitter
     */
    @PostMapping("/stream")
    public SseEmitter streamChat(@RequestBody @Valid ChatMessageDTO chatMessageDTO) {
        SseEmitter sseEmitter = new SseEmitter();
        aiChatService.streamChat(chatMessageDTO, sseEmitter);
        return sseEmitter;
    }
}
