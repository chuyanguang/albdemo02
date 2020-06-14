package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@SpringBootTest
class RedisTest {

    //    @Autowired
//    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test01() {
        String key1 = "key0001";
        Boolean hasKey = redisTemplate.hasKey(key1);
        log.info("{}:{}", key1, hasKey);
        if (hasKey) {
            String val = redisTemplate.opsForValue().get(key1);
            log.info("{}:{}", key1, val);
        }
    }

//    @Test
//    void contextLoads() {
//        String key1 = "key0003";
//        boolean hasKey = redisUtil.hasKey(key1);
//        log.info("{}:{}", key1 ,hasKey);
//        if (hasKey){
//            Object val = redisUtil.get(key1);
//            log.info("{}:{}", key1, val);
//        }
//    }


}
