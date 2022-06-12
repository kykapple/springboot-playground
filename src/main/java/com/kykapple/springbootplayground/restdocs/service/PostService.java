package com.kykapple.springbootplayground.restdocs.service;

import com.kykapple.springbootplayground.restdocs.domain.Post;
import com.kykapple.springbootplayground.restdocs.domain.repository.PostRepository;
import com.kykapple.springbootplayground.restdocs.service.dto.CreatePostRequestDto;
import com.kykapple.springbootplayground.restdocs.service.dto.PostConverter;
import com.kykapple.springbootplayground.restdocs.service.dto.PostResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Long createPost(CreatePostRequestDto createPostRequestDto) {
        Post post = PostConverter.toPost(createPostRequestDto);
        Post savedPost = postRepository.save(post);

        return savedPost.getId();
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);

        return PostConverter.toPostResponseDto(post);
    }

}
