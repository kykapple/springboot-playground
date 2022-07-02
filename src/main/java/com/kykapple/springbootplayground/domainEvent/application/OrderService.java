package com.kykapple.springbootplayground.domainEvent.application;

import com.kykapple.springbootplayground.domainEvent.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrderService {

    public void cancelOrder(Order order) {
        order.cancel();
    }

}
