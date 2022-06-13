package com.kykapple.springbootplayground.pagination.comment.service.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    private Long postId;
    private String writer;
    private String contents;

    public CreateCommentRequestDto(Long postId, String writer, String contents) {
        this.postId = postId;
        this.writer = writer;
        this.contents = contents;
    }

}
