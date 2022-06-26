package com.kykapple.springbootplayground.domainEvent.event;

import lombok.Getter;

@Getter
public class OrderCanceledEvent {

    private String orderNo;

    public OrderCanceledEvent(String orderNo) {
        this.orderNo = orderNo;
    }

}
