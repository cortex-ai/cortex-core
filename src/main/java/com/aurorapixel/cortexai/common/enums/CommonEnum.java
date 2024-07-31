package com.aurorapixel.cortexai.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommonEnum {
    SUCCESS(0, "No"),
    FAIL(1, "Yes"),
    ;
    private final int code;
    private final String message;
}
