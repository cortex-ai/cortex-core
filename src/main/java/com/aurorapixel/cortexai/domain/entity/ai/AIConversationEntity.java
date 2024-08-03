package com.aurorapixel.cortexai.domain.entity.ai;


import com.aurorapixel.cortexai.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("ai_conversation")
@EqualsAndHashCode(callSuper = true)
public class AIConversationEntity extends BaseEntity {
    /**
     * 会话id
     */
    private String conversationId;

    /**
     * 会话总结
     */
    private String conversationSummary;

    /**
     * 用户id
     */
    private Long userId;
}
