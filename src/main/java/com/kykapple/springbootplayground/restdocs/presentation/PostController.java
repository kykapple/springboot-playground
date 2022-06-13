package com.kykapple.springbootplayground.restdocs.presentation;

import com.kykapple.springbootplayground.restdocs.presentation.dto.CreatePostRequest;
import com.kykapple.springbootplayground.restdocs.presentation.dto.PostConverter;
import com.kykapple.springbootplayground.restdocs.presentation.dto.PostResponse;
import com.kykapple.springbootplayground.restdocs.service.PostService;
import com.kykapple.springbootplayground.restdocs.service.dto.CreatePostRequestDto;
import com.kykapple.springbootplayground.restdocs.service.dto.PostResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

//@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/posts")
    public ResponseEntity<Void> createPost(CreatePostRequest createPostRequest) {
        CreatePostRequestDto createPostRequestDto = PostConverter.toCreatePostResponseDto(createPostRequest);

        Long postId = postService.createPost(createPostRequestDto);
        URI redirectUri = URI.create("/api/post/" + postId);

        return ResponseEntity.created(redirectUri)
                .build();
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.getPost(postId);

        return ResponseEntity.ok(PostConverter.toPostResponse(postResponseDto));
    }

}
