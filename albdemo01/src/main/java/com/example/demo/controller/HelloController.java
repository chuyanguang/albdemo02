package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.demo.entity.Result;
import com.example.demo.handler.HelloHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "hello",tags = "hello")
@RestController
@RequestMapping("hello")
public class HelloController {

    @ApiOperation(value = "test", notes = "test01")
    @GetMapping(value = "/say/{name}")
    @SentinelResource(value = "sayname",
            blockHandlerClass = HelloHandler.class,
            blockHandler = "sayHelloDefault",
            fallback = "sayHelloExp")
    public Result sayHello(@ApiParam(name = "name", value = "名字")
                               @PathVariable(value = "name") String name) {
        int count = 10/0;
        log.info("hello {}", name);
        return new Result(200, "成功", name);
    }

    public Result sayHelloExp(String name, Throwable  e){
        log.info("sayHelloDefault, {}", e.getMessage());
        return new Result(200, "异常处理", name);
    }
}
