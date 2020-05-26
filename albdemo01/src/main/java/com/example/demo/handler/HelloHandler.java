package com.example.demo.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.demo.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloHandler {

    public static Result sayHelloDefault(String name, BlockException ble){
        log.info("sayHelloDefault, {}", ble.getMessage());
        return new Result(HttpStatus.OK, "降级", name);
    }

}
