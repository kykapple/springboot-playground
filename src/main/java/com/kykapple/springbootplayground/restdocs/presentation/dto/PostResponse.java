package com.kykapple.springbootplayground.restdocs.presentation.dto;

import lombok.Getter;

@Getter
public class PostResponse {

    private String title;
    private String contents;

    public PostResponse(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
