package com.aurorapixel.cortexai.application.factory;

import cn.hutool.core.util.StrUtil;
import com.aurorapixel.cortexai.common.constants.AIConstant;
import com.aurorapixel.cortexai.common.enums.ai.ChatModelEnum;
import com.aurorapixel.cortexai.common.exception.ServiceException;
import com.aurorapixel.cortexai.common.utils.AuthUtil;
import com.aurorapixel.cortexai.domain.entity.ai.AIModelConfigEntity;
import com.aurorapixel.cortexai.domain.repository.ai.AIModelConfigRepository;
import com.aurorapixel.cortexai.domain.repository.ai.AITokenUserRepository;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AIProviderFactory {
    private final AIModelConfigRepository aiModelConfigRepository;
    private final AITokenUserRepository aiTokenUSerRepository;


    /**
     * 用户选择模型
     * @param modelName 模型名称
     * @return 聊天模型
     */
    public ChatLanguageModel getChatLanguageModel(String modelName) {
        AIModelConfigEntity modelConfig = getAiModelConfigEntity(modelName);
        String apiKey = modelConfig.getApiKey();
        String baseUrl = modelConfig.getBaseUrl();
        if(StrUtil.isEmpty(apiKey)){
            throw new ServiceException("未设置模型ApiKey");
        }
        if(StrUtil.isEmpty(baseUrl)){
            throw new ServiceException("未设置模型BaseUrl");
        }
        ChatModelEnum chatModelEnum = ChatModelEnum.valueOf(modelName);
        return switch (chatModelEnum) {
            case GPT_4O, GPT_3_5 -> getOpenAIChatModel(modelName, baseUrl, apiKey);
            default -> throw new ServiceException("不支持的模型类型");
        };
    }


    /**
     * 用户选择Streaming模型
     * @param modelName 模型名称
     * @return 聊天模型
     */
    public StreamingChatLanguageModel getStreamingChatLanguageModel(String modelName) {
        AIModelConfigEntity modelConfig = getAiModelConfigEntity(modelName);
        String apiKey = modelConfig.getApiKey();
        String baseUrl = modelConfig.getBaseUrl();
        if(StrUtil.isEmpty(apiKey)){
            throw new ServiceException("未设置模型ApiKey");
        }
        if(StrUtil.isEmpty(baseUrl)){
            throw new ServiceException("未设置模型BaseUrl");
        }
        ChatModelEnum chatModelEnum = ChatModelEnum.valueOf(modelName);
        return switch (chatModelEnum) {
            case GPT_4O, GPT_3_5 -> getStreamingOpenAIChatModel(modelName, baseUrl, apiKey);
            default -> throw new ServiceException("不支持的模型类型");
        };
    }

    /**
     * 获取免费试用模型
     * @return 免费试用模型
     */
    public ChatLanguageModel getFreeChatLanguageModel() {
        return getOpenAIChatModel(ChatModelEnum.GPT_3_5.getModelName(), AIConstant.OPEN_AI_BASE_URL_CHAT_ANYWHERE, AIConstant.OPEN_KEY);
    }


    /**
     * openai 模型
     * @param modelName 模型名称
     * @param baseUrl 模型地址
     * @param apiKey 模型key
     * @return 模型
     */
    private OpenAiChatModel getOpenAIChatModel(String modelName,String baseUrl,String apiKey) {
        return OpenAiChatModel.builder().modelName(modelName).baseUrl(baseUrl).apiKey(apiKey).build();
    }


    /**
     * openai 流式聊天模型
     * @param modelName 模型名称
     * @param baseUrl 模型地址
     * @param apiKey 模型key
     * @return 模型
     */
    private StreamingChatLanguageModel getStreamingOpenAIChatModel(String modelName,String baseUrl,String apiKey) {
        return OpenAiStreamingChatModel.builder().modelName(modelName).baseUrl(baseUrl).apiKey(apiKey).build();
    }


    /**
     * 获取模型配置
     * @param modelName 模型名称
     * @return 模型配置
     */
    private AIModelConfigEntity getAiModelConfigEntity(String modelName) {
        if (StrUtil.isEmpty(modelName)) {
            throw new ServiceException("模型名称不允许为空");
        }
        //判断用户是否登录
        if (Objects.isNull(AuthUtil.getUserId())) {
            throw new ServiceException("请先登录再使用!");
        }
        //获取模型配置
        Optional<AIModelConfigEntity> modelConfigOptional = aiModelConfigRepository.findByUserIdAndModelName(AuthUtil.getUserId(), modelName);
        AIModelConfigEntity modelConfig = modelConfigOptional.orElseGet
                (
                        () -> aiModelConfigRepository.findSystemDefaultByModelName(modelName).orElseThrow
                                (
                                        () -> new ServiceException("未找到模型配置")
                                )
                );
        return modelConfig;
    }

}

