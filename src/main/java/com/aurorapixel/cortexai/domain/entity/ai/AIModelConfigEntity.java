package com.aurorapixel.cortexai.domain.entity.ai;

import com.aurorapixel.cortexai.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户大模型配置
 */
@Data
@TableName("ai_model_config")
@EqualsAndHashCode(callSuper = true)
public class AIModelConfigEntity extends BaseEntity {
    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 供应商名
     */
    private String providerName;

    /**
     * 站点路径
     */
    private String baseUrl;

    /**
     * apikey
     */
    private String apiKey;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 是否默认 0-否 1-是
     */
    private Integer isDefault;
}
