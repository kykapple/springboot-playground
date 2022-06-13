package com.kykapple.springbootplayground.pagination;

import com.kykapple.springbootplayground.pagination.post.service.PostService;
import com.kykapple.springbootplayground.pagination.post.service.dto.CreatePostRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DummyData {

    @Autowired
    private PostService postService;

    @Test
    void setUp() {
        for (int i = 3; i <= 1_000_000; i++) {
            CreatePostRequestDto createPostRequestDto = new CreatePostRequestDto("title", "post contents" + i, List.of("java", "spring", "jpa"));
            postService.writePost(createPostRequestDto);
        }
    }

}
