package com.kykapple.springbootplayground.pagination.post.service;

import com.kykapple.springbootplayground.pagination.post.service.dto.CreatePostRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostServiceTest {

    @Autowired
    private PostService postService;

    @BeforeEach
    void setUp() {
        postService.writePost(new CreatePostRequestDto("kyk", "origin", null));
    }

    @Test
    void optimisticLockDeletionTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(new UpdateWorker(countDownLatch));
        thread.start();

        Thread.sleep(1000);
        postService.deletePost("kyk");

        countDownLatch.await();

        Long id = postService.findPostByWriter("kyk");
        assertThat(id).isNull();
    }

    private class UpdateWorker implements Runnable {

        private CountDownLatch countDownLatch;

        public UpdateWorker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            postService.updatePost("kyk", "modified");
            countDownLatch.countDown();
        }
    }

}