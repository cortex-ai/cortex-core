package com.aurorapixel.cortexai.common.enums.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatModelEnum {

    GPT_3_5("gpt-3.5-turbo","openai","chat"),
    GPT_4("gpt-4","openai","chat"),
    GPT_4O("gpt-4o","openai","chat"),
    ;


    private final String modelName;
    private final String provider;
    private final String use;


    public static ChatModelEnum getEnum(String modelName) {
        for (ChatModelEnum modelEnum : ChatModelEnum.values()) {
            if (modelEnum.modelName.equals(modelName)) {
                return modelEnum;
            }
        }
        return null;
    }

}
