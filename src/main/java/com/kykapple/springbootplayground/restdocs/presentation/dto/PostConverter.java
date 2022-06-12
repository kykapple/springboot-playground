package com.kykapple.springbootplayground.restdocs.presentation.dto;

import com.kykapple.springbootplayground.restdocs.service.dto.CreatePostRequestDto;
import com.kykapple.springbootplayground.restdocs.service.dto.PostResponseDto;

public class PostConverter {

    public static CreatePostRequestDto toCreatePostResponseDto(CreatePostRequest createPostRequest) {
        return new CreatePostRequestDto(createPostRequest.getTitle(), createPostRequest.getContents());
    }

    public static PostResponse toPostResponse(PostResponseDto postResponseDto) {
        return new PostResponse(postResponseDto.getTitle(), postResponseDto.getContents());
    }

}
