package com.kykapple.springbootplayground.pagination.post.service.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CreatePostRequestDto {

    private String writer;
    private String contents;
    private List<String> tags;

    public CreatePostRequestDto(String writer, String contents, List<String> tags) {
        this.writer = writer;
        this.contents = contents;
        this.tags = tags;
    }

}
