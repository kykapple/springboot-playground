package com.kykapple.springbootplayground.pagination.comment.service.dto;

import com.kykapple.springbootplayground.pagination.comment.domain.Comment;

public class CommentConverter {

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return new CommentResponseDto(comment.getWriter(), comment.getContents());
    }

}
