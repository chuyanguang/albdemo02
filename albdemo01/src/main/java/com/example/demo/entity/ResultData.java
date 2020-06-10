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

}
