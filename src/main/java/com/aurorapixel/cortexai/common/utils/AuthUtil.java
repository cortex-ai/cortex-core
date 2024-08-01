package com.aurorapixel.cortexai.common.utils;

import com.aurorapixel.cortexai.common.constants.CommonConstant;

public class AuthUtil {
    private AuthUtil() {
        throw new UnsupportedOperationException("这是一个工具类，不能实例化。");
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        return null;
    }

    public static Long getIp() {
        //未登录用户默认-1
        return -1L;
    }
}
