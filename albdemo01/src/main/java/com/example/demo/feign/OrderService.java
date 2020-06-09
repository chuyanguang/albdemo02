package com.example.demo.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "albdemo02", fallbackFactory = OrderServiceFallbackFactory.class)
public interface OrderService {

    @GetMapping("/order/getAll")
    String getAllOrders();

}

@Slf4j
@Component
class OrderServiceFallbackFactory implements FallbackFactory<OrderService> {
    @Override
    public OrderService create(Throwable throwable) {
        return new OrderService() {
            @Override
            public String getAllOrders() {
                log.warn(throwable.toString());
                return "service exception";
            }
        };
    }
}