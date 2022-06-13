package com.kykapple.springbootplayground.pagination.comment.service.dto;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private String writer;
    private String contents;

    public CommentResponseDto(String writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }

}
