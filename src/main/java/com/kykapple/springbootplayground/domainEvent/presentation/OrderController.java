package com.kykapple.springbootplayground.domainEvent.presentation;

import com.kykapple.springbootplayground.domainEvent.application.OrderService;
import com.kykapple.springbootplayground.domainEvent.presentation.dto.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("api/orders")
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }

}
