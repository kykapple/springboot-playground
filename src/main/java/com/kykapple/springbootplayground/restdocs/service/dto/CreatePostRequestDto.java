package com.kykapple.springbootplayground.restdocs.service.dto;

import lombok.Getter;

@Getter
public class CreatePostRequestDto {

    private String title;
    private String contents;

    public CreatePostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
