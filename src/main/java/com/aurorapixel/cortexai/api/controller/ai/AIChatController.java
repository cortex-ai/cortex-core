package com.aurorapixel.cortexai.api.controller.ai;

import com.aurorapixel.cortexai.annotation.CortexAIController;
import com.aurorapixel.cortexai.api.dto.ChatMessageDTO;
import com.aurorapixel.cortexai.application.service.AIService;
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
    private AIService aiService;

    /**
     * stream方式发送消息
     * @param chatMessageDTO 消息请求体
     * @return SseEmitter
     */
    @PostMapping("/stream")
    public SseEmitter sendMessage3(@RequestBody @Valid ChatMessageDTO chatMessageDTO) {
        SseEmitter sseEmitter = new SseEmitter();
        aiService.streamChat(chatMessageDTO, sseEmitter);
        return sseEmitter;
    }
}
