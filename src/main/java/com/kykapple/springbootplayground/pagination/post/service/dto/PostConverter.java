package com.kykapple.springbootplayground.pagination.post.service.dto;

import com.kykapple.springbootplayground.pagination.comment.service.dto.CommentConverter;
import com.kykapple.springbootplayground.pagination.comment.service.dto.CommentResponseDto;
import com.kykapple.springbootplayground.pagination.post.domain.Post;
import com.kykapple.springbootplayground.pagination.post.domain.PostTag;
import com.kykapple.springbootplayground.pagination.tag.domain.Tag;

import java.util.List;
import java.util.stream.Collectors;

public class PostConverter {

    public static List<PostResponse> toPostResponseDtoList(List<Post> posts) {
        return posts.stream()
                .map(PostConverter::toPostResponseDto)
                .collect(Collectors.toList());
    }

    public static PostResponse toPostResponseDto(Post post) {
        List<CommentResponseDto> comments = post.getComments()
                .stream()
                .map(CommentConverter::toCommentResponseDto)
                .collect(Collectors.toList());

        List<String> tags = post.getTags()
                .stream()
                .map(PostTag::getTag)
                .map(Tag::getName)
                .collect(Collectors.toList());

        return PostResponse.builder()
                .writer(post.getWriter())
                .contents(post.getContents())
                .comments(comments)
                .tags(tags)
                .build();
    }

}
