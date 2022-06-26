package com.kykapple.springbootplayground.domainEvent.application;

import com.kykapple.springbootplayground.domainEvent.event.OrderCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    @Async
    @EventListener(OrderCanceledEvent.class)
    public void refundOrder(OrderCanceledEvent event) {
        System.out.println("////////이벤트 처리////////");
        System.out.println(Thread.currentThread().getName());
        System.out.println(event.getOrderNo());
        System.out.println("/////////////////////////");
    }

}
