package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @GetMapping(value = "getAll")
    public String getOrders(){
        int result = 10/0;
        return "there is no order!";
    }


}
