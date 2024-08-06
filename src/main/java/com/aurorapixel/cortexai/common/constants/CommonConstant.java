package com.aurorapixel.cortexai.common.constants;


public class CommonConstant {
    private CommonConstant() {
        throw new UnsupportedOperationException("这是一个常量类，不能实例化。");
    }

    /**
     * 时间格式化标准
     */
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 用户登录类型
     */
    public static final String USER_TYPE = "User-Type";

    public static final Integer YES = 1;

    public static final Integer NO = 0;

    public static final Integer IGNORE = -1;
}
