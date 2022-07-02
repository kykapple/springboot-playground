package com.kykapple.springbootplayground.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kykapple.springbootplayground.rabbitMQ.model.MessageModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    public Producer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishMessage(MessageModel messageModel) {
        rabbitTemplate.convertAndSend(exchange, routingKey, messageModel);
    }

}
