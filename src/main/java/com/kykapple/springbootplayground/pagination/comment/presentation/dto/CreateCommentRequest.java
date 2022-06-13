package com.kykapple.springbootplayground.pagination.comment.presentation.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private String writer;
    private String contents;

}
