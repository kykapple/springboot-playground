package com.kykapple.springbootplayground.mongodb.service.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {

    private String name;
    private int age;

    public UserRequestDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
