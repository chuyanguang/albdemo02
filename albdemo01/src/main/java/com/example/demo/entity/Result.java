package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

}
