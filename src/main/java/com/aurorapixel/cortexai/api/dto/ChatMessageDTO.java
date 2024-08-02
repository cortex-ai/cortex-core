package com.aurorapixel.cortexai.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatMessageDTO {
    /**
     * 聊天内容
     */
    @NotEmpty
    private String content;

    /**
     * 模型名称
     */
    @NotEmpty
    private String modelName;

    /**
     * 会话ID
     */
    private String conversationId;

    /**
     * 是否启用上下文
     */
    @NotNull
    private Boolean isContext = false;

    /**
     * 上下文最大条数
     */
    private Integer maxIndex = 20;
}
