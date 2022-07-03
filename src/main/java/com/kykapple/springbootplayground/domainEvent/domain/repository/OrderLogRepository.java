package com.kykapple.springbootplayground.domainEvent.domain.repository;

import com.kykapple.springbootplayground.domainEvent.domain.OrderLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderLogRepository extends MongoRepository<OrderLog, String> {
}
