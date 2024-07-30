package com.aurorapixel.cortexai.api.response;


import lombok.*;

/**
 * 全局返回体包装类
 * @param <T>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class R<T> {
    private int code;
    private String message;
    private T data;

    public static <T> R<T> ok(T data) {
        return new R<T>(0, "success", data);
    }

    public static <T> R<T> ok(T data, String message) {
        return new R<T>(0, message, data);
    }

    public static <T> R<T> fail(int code, String message) {
        return new R<T>(code, message, null);
    }

    public static <T> R<T> fail(int code, String message, T data) {
        return new R<T>(code, message, data);
    }

    public static <T> R<T> fail(String message) {
        return new R<T>(500, message, null);
    }
}
