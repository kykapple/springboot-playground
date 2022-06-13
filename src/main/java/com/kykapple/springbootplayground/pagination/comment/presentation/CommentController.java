package com.kykapple.springbootplayground.pagination.comment.presentation;

import com.kykapple.springbootplayground.pagination.comment.presentation.dto.CommentConverter;
import com.kykapple.springbootplayground.pagination.comment.presentation.dto.CreateCommentRequest;
import com.kykapple.springbootplayground.pagination.comment.service.CommentService;
import com.kykapple.springbootplayground.pagination.comment.service.dto.CreateCommentRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/posts/{postId}/comments")
    public ResponseEntity<Void> addComment(@PathVariable Long postId, @RequestBody CreateCommentRequest createCommentRequest) {
        CreateCommentRequestDto createCommentRequestDto = CommentConverter.toCreateCommentRequestDto(postId, createCommentRequest);
        commentService.addComment(createCommentRequestDto);

        return ResponseEntity.ok()
                .build();
    }

}
