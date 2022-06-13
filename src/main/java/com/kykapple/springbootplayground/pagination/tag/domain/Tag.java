package com.kykapple.springbootplayground.pagination.tag.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

}
