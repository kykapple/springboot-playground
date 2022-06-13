package com.kykapple.springbootplayground.pagination.post.presentation.dto;

import com.kykapple.springbootplayground.pagination.post.service.dto.CreatePostRequestDto;

public class PostConverter {

    public static CreatePostRequestDto toCreatePostRequestDto(CreatePostRequest createPostRequest) {
        return new CreatePostRequestDto(createPostRequest.getWriter(), createPostRequest.getContents(), createPostRequest.getTags());
    }

}
