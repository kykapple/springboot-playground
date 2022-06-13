package com.kykapple.springbootplayground.pagination.post.service.dto;

import com.kykapple.springbootplayground.pagination.comment.service.dto.CommentResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponse {

    private String writer;
    private String contents;
    private List<String> tags;
    private List<CommentResponseDto> comments;

    @Builder
    public PostResponse(String writer, String contents, List<String> tags, List<CommentResponseDto> comments) {
        this.writer = writer;
        this.contents = contents;
        this.tags = tags;
        this.comments = comments;
    }

}
