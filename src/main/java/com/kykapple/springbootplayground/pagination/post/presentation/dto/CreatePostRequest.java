package com.kykapple.springbootplayground.pagination.post.presentation.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CreatePostRequest {

    private String writer;
    private String contents;
    private List<String> tags;

}
