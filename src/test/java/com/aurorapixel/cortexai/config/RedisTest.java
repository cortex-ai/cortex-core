package com.aurorapixel.cortexai.config;

import com.aurorapixel.cortexai.common.config.redis.RedisComponent;
import com.aurorapixel.cortexai.pojo.UserTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisComponent redisComponent;

    @Test
    public void connectTest() {
        redisComponent.set("key", "value");
        System.out.println(redisComponent.get("key"));
    }

    @Test
    public void setObjectTest() {
        List<UserTest> tests = new ArrayList<>();
        UserTest userTest = new UserTest();
        userTest.setAge(1);
        userTest.setName("test");
        tests.add(userTest);
        UserTest userTest2 = new UserTest();
        userTest2.setAge(2);
        userTest2.setName("test2");
        tests.add(userTest2);
        redisComponent.set("key", tests);
        List<UserTest> key = redisComponent.getList("key", UserTest.class);
        System.out.println(key);
    }
}
