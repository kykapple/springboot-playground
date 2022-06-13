package com.kykapple.springbootplayground.pagination.post.presentation;

import com.kykapple.springbootplayground.pagination.post.presentation.dto.CreatePostRequest;
import com.kykapple.springbootplayground.pagination.post.presentation.dto.PostConverter;
import com.kykapple.springbootplayground.pagination.post.service.PostService;
import com.kykapple.springbootplayground.pagination.post.service.dto.CreatePostRequestDto;
import com.kykapple.springbootplayground.pagination.post.service.dto.PostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/post")
    public ResponseEntity<Void> writePost(@RequestBody CreatePostRequest createPostRequest) {
        CreatePostRequestDto createPostRequestDto = PostConverter.toCreatePostRequestDto(createPostRequest);
        postService.writePost(createPostRequestDto);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/api/post")
    public ResponseEntity<List<PostResponse>> readPost(@PageableDefault Pageable pageable) {
        List<PostResponse> postResponseList = postService.readPost(pageable);

        return ResponseEntity.ok(postResponseList);
    }

}
