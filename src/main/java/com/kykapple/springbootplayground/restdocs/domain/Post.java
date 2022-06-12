package com.kykapple.springbootplayground.restdocs.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    public Post() {
    }

    @Builder
    public Post(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
