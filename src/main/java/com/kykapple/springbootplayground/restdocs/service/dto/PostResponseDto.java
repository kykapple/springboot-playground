package com.kykapple.springbootplayground.restdocs.service.dto;

import lombok.Getter;

@Getter
public class PostResponseDto {

    private String title;
    private String contents;

    public PostResponseDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
