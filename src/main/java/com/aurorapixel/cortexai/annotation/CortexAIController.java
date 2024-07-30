package com.aurorapixel.cortexai.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@Validated
@RequestMapping
public @interface CortexAIController {
    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value() default {};

    boolean isValid() default true;
}
