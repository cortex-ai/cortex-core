package com.aurorapixel.cortexai.api.handler;


import com.aurorapixel.cortexai.api.response.R;
import com.aurorapixel.cortexai.application.exception.ServiceException;
import jakarta.validation.ValidationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局状态拦截器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R<?> handleDefaultException(Exception ex) {
        ex.printStackTrace();
        return R.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统异常!");
    }



    /**
     * 全局参数校验异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> handleValidationException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return R.fail(HttpStatus.BAD_REQUEST.value(), "参数校验失败", errors);
    }

    /**
     * 参数校验异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public R<?> validationException(ValidationException ex) {
        ex.printStackTrace();
        String[] splitErrorMessages = ex.getMessage().contains(",") ? ex.getMessage().split(",") : new String[]{ex.getMessage()};
        StringBuilder message = new StringBuilder();

        for (String error : splitErrorMessages) {
            // 按冒号分割，并保留方法名后的部分
            String[] parts = error.split(":");
            if (parts.length > 1) {
                String[] subParts = parts[0].split("\\.");
                String fieldName = subParts[subParts.length - 1].trim(); // 获取字段名
                message.append(fieldName).append(":").append(parts[1].trim()); // 只保留字段名和错误信息
                message.append(", ");
            } else {
                message.append(parts[0].trim()); // 如果没有冒号，只保留原始信息
                message.append(", ");
            }
        }

        // 删除最后一个多余的逗号和空格
        if (message.length() > 0) {
            message.setLength(message.length() - 2);
        }

        return R.fail(HttpStatus.BAD_REQUEST.value(), "参数校验失败:"+message.toString());
    }

    /**
     * 自定义业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public R<?> handleServiceException(ServiceException ex) {
        ex.printStackTrace();
        return R.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 请求参数丢失异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingRequestValueException.class)
    public R<?> handleMissingRequestValueException(MissingRequestValueException ex) {
        ex.printStackTrace();
        return R.fail(HttpStatus.BAD_REQUEST.value(),"请求参数丢失");
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public R<?> handleHttpMessageConversionException(HttpMessageConversionException ex) {
        ex.printStackTrace();
        return R.fail(HttpStatus.BAD_REQUEST.value(),"请求体丢失");
    }

    /**
     * 参数类型匹配异常
     * @param ex
     * @return
     */
    @ExceptionHandler(TypeMismatchException.class)
    public R<?> handleTypeMismatchException(TypeMismatchException ex) {
        ex.printStackTrace();
        return R.fail(HttpStatus.BAD_REQUEST.value(), "参数类型不匹配");
    }
}
