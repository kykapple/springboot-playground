package com.kykapple.springbootplayground.restdocs.domain.repository;

import com.kykapple.springbootplayground.restdocs.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long postId);

}
