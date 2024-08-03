package com.aurorapixel.cortexai.domain.entity.ai;

import com.aurorapixel.cortexai.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("ai_conversation_message")
@EqualsAndHashCode(callSuper = true)
public class AIConversationMessageEntity extends BaseEntity {
    /**
     * 会话id
     */
    private Long conversationId;

    /**
     * 内容
     */
    private String content;

    /**
     * 角色[user,ai,system]
     */
    private String roleType;

    /**
     * 状态
     */
    private Integer status;
}
