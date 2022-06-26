package com.kykapple.springbootplayground.domainEvent.application;

import com.kykapple.springbootplayground.domainEvent.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void eventTest() {
        Order order = new Order("book:001");
        orderService.cancelOrder(order);

        System.out.println(order.getState());
    }

}