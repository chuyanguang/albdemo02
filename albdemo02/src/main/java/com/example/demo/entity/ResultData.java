package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ResultData<T> {

    private HttpStatus code;

    private String msg;

    private T data;

    public static <T> ResultData ok(String msg, T data){
        return new ResultData(HttpStatus.OK, msg, data);
    }

    public static <T> ResultData fail(String msg, T data){
        return new ResultData(HttpStatus.BAD_REQUEST, msg, data);
    }

}
