package com.aurorapixel.cortexai.infrastructure.aiprovider;

import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.stereotype.Component;

@Component
public class AIProviderFactory {
    public ChatLanguageModel openAI() {
        return OpenAiChatModel.builder().baseUrl("").apiKey("").modelName("").build();
    }

    public ChatLanguageModel google() {
        return AzureOpenAiChatModel.builder().endpoint("").apiKey("").deploymentName("").build();
    }
}
