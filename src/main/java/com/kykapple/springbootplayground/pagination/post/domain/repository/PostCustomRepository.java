package com.kykapple.springbootplayground.pagination.post.domain.repository;

import com.kykapple.springbootplayground.pagination.post.domain.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostCustomRepository {

    List<Post> findPostsBetweenDate(LocalDateTime start, LocalDateTime end);

    Optional<Post> findPostByWriterName(String writer);

}
