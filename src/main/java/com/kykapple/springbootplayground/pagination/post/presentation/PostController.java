package com.kykapple.springbootplayground.pagination.post.presentation;

import com.kykapple.springbootplayground.pagination.post.presentation.dto.CreatePostRequest;
import com.kykapple.springbootplayground.pagination.post.presentation.dto.PostConverter;
import com.kykapple.springbootplayground.pagination.post.service.PostService;
import com.kykapple.springbootplayground.pagination.post.service.dto.CreatePostRequestDto;
import com.kykapple.springbootplayground.pagination.post.service.dto.PostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/posts")
    public ResponseEntity<Void> writePost(@RequestBody CreatePostRequest createPostRequest) {
        CreatePostRequestDto createPostRequestDto = PostConverter.toCreatePostRequestDto(createPostRequest);
        postService.writePost(createPostRequestDto);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostResponse>> readPost(@PageableDefault Pageable pageable) {
        List<PostResponse> postResponseList = postService.readPost(pageable);

        return ResponseEntity.ok(postResponseList);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/api/posts/{writer}")
    public ResponseEntity<Long> findPostByWriter(@PathVariable String writer) {
        Long postId = postService.findPostByWriter(writer);

        return ResponseEntity.ok(postId);
    }

    @GetMapping("/api/posts/date")
    public ResponseEntity<List<PostResponse>> findPostBetweenDate(
            @RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        List<PostResponse> postResponseList = postService.findPostsBetweenDate(start, end);

        return ResponseEntity.ok(postResponseList);
    }

}
