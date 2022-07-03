package com.kykapple.springbootplayground.mongodb.domain;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Document(collection = "user")
public class User {

    @Id
    private String _id;

    private String name;

    private int age;

    @NotNull
    @CreatedDate
    @Field("registered_at")
    private LocalDateTime registeredAt;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
