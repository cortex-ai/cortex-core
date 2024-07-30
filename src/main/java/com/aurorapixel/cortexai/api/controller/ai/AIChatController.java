package com.aurorapixel.cortexai.api.controller.ai;

import com.aurorapixel.cortexai.annotation.CortexAIController;
import com.aurorapixel.cortexai.api.response.R;
import com.aurorapixel.cortexai.application.dto.ChatMessageDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.*;

/**
 * AI-chat应用
 */
@CortexAIController("/ai/chat")
public class AIChatController {
    /**
     * 测试接口
     */
    @GetMapping("/test")
    public R<String> sendMessage(@RequestParam @NotEmpty @NotBlank String name) {
        return R.ok("Hello World: " + name);
    }

    @GetMapping("/test/{id}")
    public R<String> sendMessage2(@PathVariable @Max(10) Long id) {
        return R.ok("Hello World: " + id);
    }

    @PostMapping("/test")
    public R<String> sendMessage3(@RequestBody @Valid ChatMessageDTO chatMessageDTO) {
        return R.ok("Hello World");
    }
}
