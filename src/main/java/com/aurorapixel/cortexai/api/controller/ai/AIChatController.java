package com.aurorapixel.cortexai.api.controller.ai;

import com.aurorapixel.cortexai.annotation.CortexAIController;
import com.aurorapixel.cortexai.api.dto.ai.AIChatMessageDTO;
import com.aurorapixel.cortexai.application.service.ai.AIChatService;
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
     * @param AIChatMessageDTO 消息请求体
     * @return SseEmitter
     */
    @PostMapping("/stream")
    public SseEmitter streamChat(@RequestBody @Valid AIChatMessageDTO AIChatMessageDTO) {
        SseEmitter sseEmitter = new SseEmitter();
        aiChatService.streamChat(AIChatMessageDTO, sseEmitter);
        return sseEmitter;
    }
}
