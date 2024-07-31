package com.aurorapixel.cortexai.common.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException  {
    private int code;

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ServiceException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST.value();
    }

    public int getCode() {
        return code;
    }
}
