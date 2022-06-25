package com.kykapple.springbootplayground.pagination.post.service;

import com.kykapple.springbootplayground.pagination.post.domain.Post;
import com.kykapple.springbootplayground.pagination.post.domain.repository.PostRepository;
import com.kykapple.springbootplayground.pagination.post.service.dto.CreatePostRequestDto;
import com.kykapple.springbootplayground.pagination.post.service.dto.PostConverter;
import com.kykapple.springbootplayground.pagination.post.service.dto.PostResponse;
import com.kykapple.springbootplayground.pagination.tag.domain.Tag;
import com.kykapple.springbootplayground.pagination.tag.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class PostService {

    private PostRepository postRepository;
    private TagService tagService;

    public PostService(PostRepository postRepository, TagService tagService) {
        this.postRepository = postRepository;
        this.tagService = tagService;
    }

    public void writePost(CreatePostRequestDto createPostRequestDto) {
        List<Tag> tags = tagService.addTags(createPostRequestDto.getTags());

        Post post = Post.builder()
                .writer(createPostRequestDto.getWriter())
                .contents(createPostRequestDto.getContents())
                .build();
        post.addTags(tags);

        postRepository.save(post);
    }

    public List<PostResponse> readPost(Pageable pageable) {
        List<Post> posts = postRepository.findAllPosts(pageable);

        return PostConverter.toPostResponseDtoList(posts);
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        postRepository.deleteById(post.getId());
    }

    public Long findPostByWriter(String writer) {
        Post post = postRepository.findPostByWriterName(writer)
                .orElseThrow(RuntimeException::new);

        return post.getId();
    }

    public List<PostResponse> findPostsBetweenDate(LocalDateTime start, LocalDateTime end) {
        List<Post> posts = postRepository.findPostsBetweenDate(start, end);

        return PostConverter.toPostResponseDtoList(posts);
    }

    public void updatePost(String writer, String contents) {
        try {
            Post post = postRepository.findPostByWriter(writer)
                    .orElseThrow(RuntimeException::new);

            Thread.sleep(3000);

            post.updateContents(contents);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(String writer) {
        postRepository.deletePostByWriter(writer);
    }

}
