package com.kykapple.springbootplayground.domainEvent.domain;

import com.kykapple.springbootplayground.domainEvent.event.OrderCanceledEvent;
import com.kykapple.springbootplayground.domainEvent.utils.EventPublisher;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNo;

    private String orderer;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    public Order() {
    }

    @Builder
    public Order(String orderNo, String orderer) {
        this.orderNo = orderNo;
        this.orderer = orderer;
        this.state = OrderState.ORDERED;
    }

    public void cancel() {
        this.state = OrderState.CANCELED;
        System.out.println("////////이벤트 발행////////");
        System.out.println(Thread.currentThread().getName());
        EventPublisher.publish(new OrderCanceledEvent(this.orderNo));
        System.out.println("/////////////////////////");
    }

}
