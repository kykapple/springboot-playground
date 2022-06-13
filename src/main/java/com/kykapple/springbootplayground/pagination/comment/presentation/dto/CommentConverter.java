package com.kykapple.springbootplayground.pagination.comment.presentation.dto;

import com.kykapple.springbootplayground.pagination.comment.service.dto.CreateCommentRequestDto;

public class CommentConverter {

    public static CreateCommentRequestDto toCreateCommentRequestDto(Long postId, CreateCommentRequest createCommentRequest) {
        return new CreateCommentRequestDto(postId, createCommentRequest.getWriter(), createCommentRequest.getContents());
    }

}
