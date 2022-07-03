package com.kykapple.springbootplayground.domainEvent.application;

import com.kykapple.springbootplayground.domainEvent.domain.Order;
import com.kykapple.springbootplayground.domainEvent.domain.OrderLog;
import com.kykapple.springbootplayground.domainEvent.domain.repository.OrderLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class OrderLogService {

    private OrderLogRepository orderLogRepository;

    public OrderLogService(OrderLogRepository orderLogRepository) {
        this.orderLogRepository = orderLogRepository;
    }

    @TransactionalEventListener(
            classes = Order.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void writeOrderLog(Order order) {
        OrderLog orderLog = OrderLog.builder()
                .orderNo(order.getOrderNo())
                .orderer(order.getOrderer())
                .state(order.getState())
                .build();
        orderLogRepository.save(orderLog);
    }

}
