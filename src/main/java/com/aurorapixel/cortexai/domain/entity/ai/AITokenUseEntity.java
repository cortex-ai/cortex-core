package com.aurorapixel.cortexai.domain.entity.ai;

import com.aurorapixel.cortexai.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户使用的AI token
 */
@Data
@TableName("ai_token_use")
@EqualsAndHashCode(callSuper = true)
public class AITokenUseEntity extends BaseEntity {
    /**
     * 大模型配置ID
     */
    private Long aiModelConfigId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 总使用量 = useTokenNum + inputTokenNum
     */
    private Integer useTokenNum;

    /**
     * 最大使用量,如果-1证明不受限
     */
    private Integer maxTokenNum;

    /**
     * 总输入量
     */
    private Integer inputTokenNum;

    /**
     * 总输出量
     */
    private Integer outputTokenNum;
}
