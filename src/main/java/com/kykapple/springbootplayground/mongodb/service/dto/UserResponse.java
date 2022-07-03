package com.kykapple.springbootplayground.mongodb.service.dto;

import com.kykapple.springbootplayground.mongodb.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse {

    private String id;
    private String name;
    private int age;

    private LocalDateTime registeredAt;

    public UserResponse(User user) {
        this.id = user.get_id().toString();
        this.name = user.getName();
        this.age = user.getAge();
        this.registeredAt = user.getRegisteredAt();
    }

}
