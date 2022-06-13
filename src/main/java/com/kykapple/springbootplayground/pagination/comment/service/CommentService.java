package com.kykapple.springbootplayground.pagination.comment.service;

import com.kykapple.springbootplayground.pagination.comment.domain.Comment;
import com.kykapple.springbootplayground.pagination.comment.domain.repository.CommentRepository;
import com.kykapple.springbootplayground.pagination.comment.service.dto.CreateCommentRequestDto;
import com.kykapple.springbootplayground.pagination.post.domain.Post;
import com.kykapple.springbootplayground.pagination.post.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public void addComment(CreateCommentRequestDto createCommentRequestDto) {
        Post post = findPostById(createCommentRequestDto.getPostId());

        Comment comment = Comment.builder()
                .post(post)
                .writer(createCommentRequestDto.getWriter())
                .contents(createCommentRequestDto.getContents())
                .build();

        commentRepository.save(comment);
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
    }

}
