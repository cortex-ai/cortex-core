package com.aurorapixel.cortexai.ai;

import com.aurorapixel.cortexai.application.service.AIChatService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.aurorapixel.cortexai.**.mapper")
public class AIChatTest {
    @Autowired
    private AIChatService chatService;

    @Test
    void ConversationSummaryTest() {
        String summary = chatService.getConversationSummary("帮我写一个js的计算器代码");
        System.out.println(summary);
    }
}
