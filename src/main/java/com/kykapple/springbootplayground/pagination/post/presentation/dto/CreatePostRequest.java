package com.kykapple.springbootplayground.pagination.post.presentation.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class CreatePostRequest {

    @NotBlank
    private String writer;

    @NotBlank
    private String contents;

    private List<String> tags;

    public CreatePostRequest(String writer, String contents, List<String> tags) {
        this.writer = writer;
        this.contents = contents;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "CreatePostRequest{" +
                "writer='" + writer + '\'' +
                ", contents='" + contents + '\'' +
                ", tags=" + tags +
                '}';
    }
}
