package com.kykapple.springbootplayground.rabbitMQ.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class MessageModel implements Serializable {

    private static final long serialVersionUID = 3601671929321966814L;
    private Long id;

    private String status;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    public MessageModel() {
    }

    public MessageModel(Long id, String status, LocalDateTime createdAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
    }

}
