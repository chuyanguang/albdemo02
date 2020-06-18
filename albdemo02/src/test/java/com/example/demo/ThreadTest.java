package com.example.demo;


import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@Slf4j
@SpringBootTest
public class ThreadTest {

    @Test
    public void test01(){
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(10));
        // 提交一组任务
        for (int i = 0; i < 10; i++) {
            final int num = i;
            completionService.submit(() -> {
                Thread.sleep(RandomUtil.randomInt(1000));
                return num;
            });
        }

        // 获取先完成的线程的结果
        for (int i = 0; i < 10; i++) {
            Future<Integer> result = null;
            try {
                result = completionService.take();
                System.out.println(result.get());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
