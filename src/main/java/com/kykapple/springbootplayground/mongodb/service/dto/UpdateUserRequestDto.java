package com.kykapple.springbootplayground.mongodb.service.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private String name;
    private int age;

    public UpdateUserRequestDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
