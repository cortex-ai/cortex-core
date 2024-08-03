package com.aurorapixel.cortexai.api.response.ai;

import lombok.Data;

@Data
public class AIChatResponse {
    /**
     * 对话唯一标识
     */
    private String  conversationUUID;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 是否错误
     */
    private Boolean isError = false;

    /**
     * 是否完成
     */
    private Boolean isFinish = false;

    /**
     * 结束理由
     */
    private String finishReason;
}
