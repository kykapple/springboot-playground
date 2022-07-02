package com.kykapple.springbootplayground.domainEvent.domain;

import com.kykapple.springbootplayground.domainEvent.event.OrderCanceledEvent;
import com.kykapple.springbootplayground.domainEvent.utils.EventPublisher;
import lombok.Getter;

@Getter
public class Order {

    private String orderNo;

    private OrderState state;

    public Order(String orderNo) {
        this.orderNo = orderNo;
        this.state = OrderState.ORDERED;
    }

    public void cancel() {
        this.state = OrderState.CANCELED;
        EventPublisher.publish(new OrderCanceledEvent(this.orderNo));
        System.out.println("////////이벤트 발행////////");
        System.out.println(Thread.currentThread().getName());
        System.out.println("/////////////////////////");
    }

}
