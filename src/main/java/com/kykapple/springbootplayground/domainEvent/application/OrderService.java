package com.kykapple.springbootplayground.domainEvent.application;

import com.kykapple.springbootplayground.domainEvent.domain.Order;
import com.kykapple.springbootplayground.domainEvent.domain.repository.OrderRepository;
import com.kykapple.springbootplayground.domainEvent.presentation.dto.OrderRequest;
import com.kykapple.springbootplayground.domainEvent.utils.EventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNo(orderRequest.getOrderNo())
                .orderer(orderRequest.getOrderer())
                .build();
        orderRepository.save(order);
        EventPublisher.publish(order);
    }

    public void cancelOrder(Order order) {
        order.cancel();
    }

}
