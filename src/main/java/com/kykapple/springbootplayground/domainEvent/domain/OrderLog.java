package com.kykapple.springbootplayground.domainEvent.domain;

import com.kykapple.springbootplayground.domainEvent.domain.OrderState;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Document(collection = "order_logs")
public class OrderLog {

    @Id
    private String id;

    private String orderNo;

    private String orderer;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @CreatedDate
    private LocalDateTime createdAt;

    public OrderLog() {
    }

    @Builder
    public OrderLog(String orderNo, String orderer, OrderState state) {
        this.orderNo = orderNo;
        this.orderer = orderer;
        this.state = state;
    }

}
