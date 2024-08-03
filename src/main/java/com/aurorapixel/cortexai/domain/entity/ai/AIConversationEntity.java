package com.aurorapixel.cortexai.domain.entity.ai;


import com.aurorapixel.cortexai.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField("conversation_uuid")
    private String conversationUUID;

    /**
     * 会话总结
     */
    private String conversationSummary;

    /**
     * 用户id
     */
    private Long userId;
}
