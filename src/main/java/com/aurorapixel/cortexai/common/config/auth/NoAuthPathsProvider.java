package com.aurorapixel.cortexai.common.config.auth;

import com.aurorapixel.cortexai.annotation.CortexAIController;
import com.aurorapixel.cortexai.annotation.NoAuth;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class NoAuthPathsProvider {
    private final ApplicationContext applicationContext;
    public List<String> getNoAuthPaths() {
        List<String> noAuthPaths = new ArrayList<>();

        String[] beanNames = applicationContext.getBeanNamesForAnnotation(CortexAIController.class);
        for (String beanName : beanNames) {
            Class<?> beanType = applicationContext.getType(beanName);
            if (beanType != null) {
                // 获取类上的 @CortexAIController 注解及其路径
                CortexAIController cortexAIController = AnnotationUtils.findAnnotation(beanType, CortexAIController.class);
                String[] classPaths = {};
                if (cortexAIController != null) {
                    classPaths = cortexAIController.value();
                }

                // 如果类上有 @NoAuth 注解，整个类的路径免授权
                if (AnnotationUtils.findAnnotation(beanType, NoAuth.class) != null) {
                    for (String classPath : classPaths) {
                        noAuthPaths.add(classPath + "/**");
                    }
                }

                // 检查方法上的 @NoAuth 注解
                for (Method method : beanType.getDeclaredMethods()) {
                    if (AnnotationUtils.findAnnotation(method, NoAuth.class) != null) {
                        // 处理方法上的映射注解
                        String[] methodPaths = getMethodPaths(method);

                        for (String classPath : classPaths) {
                            for (String methodPath : methodPaths) {
                                noAuthPaths.add(classPath + methodPath);
                            }
                        }

                        // 如果类上没有 @CortexAIController 注解，只处理方法上的路径
                        if (classPaths.length == 0) {
                            for (String methodPath : methodPaths) {
                                noAuthPaths.add(methodPath);
                            }
                        }
                    }
                }
            }
        }

        return noAuthPaths;
    }

    private String[] getMethodPaths(Method method) {
        // 处理不同类型的映射注解
        RequestMapping methodMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
        PutMapping putMapping = AnnotationUtils.findAnnotation(method, PutMapping.class);
        DeleteMapping deleteMapping = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
        PatchMapping patchMapping = AnnotationUtils.findAnnotation(method, PatchMapping.class);

        if (methodMapping != null&&methodMapping.value().length>0) {
            return methodMapping.value();
        } else if (getMapping != null) {
            return getMapping.value();
        } else if (postMapping != null) {
            return postMapping.value();
        } else if (putMapping != null) {
            return putMapping.value();
        } else if (deleteMapping != null) {
            return deleteMapping.value();
        } else if (patchMapping != null) {
            return patchMapping.value();
        } else {
            return new String[]{};
        }
    }
}
