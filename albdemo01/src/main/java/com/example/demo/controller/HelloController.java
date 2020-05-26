package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.demo.entity.Result;
import com.example.demo.handler.HelloHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Api(value = "hello",tags = "hello")
@RefreshScope
@RestController
@RequestMapping("hello")
public class HelloController {

    @Value("${test.bl}")
    private String bl01;

    @ApiOperation(value = "test", notes = "test01")
    @GetMapping(value = "/say/{name}")
    @SentinelResource(value = "sayname", blockHandlerClass = HelloHandler.class, blockHandler = "sayHelloDefault", fallback = "sayHelloExp")
    public Result sayHello(@ApiParam(name = "name", value = "名字") @PathVariable(value = "name") String name) {
        int count = 10/0;
        log.info("hello {}", name);
        return new Result(HttpStatus.OK, "成功", name);
    }

    @GetMapping("getBL")
    public Result<String> getBL(){
        return new Result(HttpStatus.OK, "成功", bl01);
    }

    @GetMapping("cj")
    public Result<String> getCJ(){
        return new Result(HttpStatus.OK, "成功", "持久化配置");
    }

    public Result sayHelloExp(String name, Throwable  e){
        log.info("sayHelloDefault, {}", e.getMessage());
        return new Result(HttpStatus.OK, "异常处理", name);
    }
}
