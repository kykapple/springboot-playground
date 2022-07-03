package com.kykapple.springbootplayground.domainEvent.domain.repository;

import com.kykapple.springbootplayground.domainEvent.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
