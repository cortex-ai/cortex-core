package com.aurorapixel.cortexai.common.utils;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AIUtil {
    private AIUtil(){
        throw new UnsupportedOperationException("这是一个工具类，不能实例化。");
    }

    public static String loadPrompt(String path){
        ClassPathResource resource = new ClassPathResource(path);
        InputStream inputStream;
        try {
            inputStream = resource.getStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            while (true){
                try {
                    if((line = bufferedReader.readLine()) == null){
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
