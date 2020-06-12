package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.dto.DeptDo;
import com.example.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        String key1 = "key0003";
        boolean hasKey = redisUtil.hasKey(key1);
        log.info("{}:{}", key1 ,hasKey);
        if (hasKey){
            Object val = redisUtil.get(key1);
            log.info("{}:{}", key1, val);
        }
    }

}
