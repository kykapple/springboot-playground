package com.kykapple.springbootplayground.restdocs.service.dto;

import com.kykapple.springbootplayground.restdocs.domain.Post;

public class PostConverter {

    public static Post toPost(CreatePostRequestDto createPostRequestDto) {
        return Post.builder()
                .title(createPostRequestDto.getTitle())
                .contents(createPostRequestDto.getContents())
                .build();
    }

    public static PostResponseDto toPostResponseDto(Post post) {
        return new PostResponseDto(post.getTitle(), post.getContents());
    }

}
