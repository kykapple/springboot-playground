package com.kykapple.springbootplayground.domainEvent.config;

import com.kykapple.springbootplayground.domainEvent.utils.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

@EnableAsync
@Configuration
public class EventConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void setEventPublisher() {
        EventPublisher.setPublisher(applicationContext);
    }

}
