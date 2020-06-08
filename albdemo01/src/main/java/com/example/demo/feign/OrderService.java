package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "albdemo02", fallback = OrderServiceException.class)
public interface OrderService {

    @GetMapping("/order/getAll")
    String getAllOrders();

}

class OrderServiceException implements OrderService{
    @Override
    public String getAllOrders() {
        return "service exception";
    }
}
