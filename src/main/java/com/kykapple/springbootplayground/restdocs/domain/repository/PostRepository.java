package com.kykapple.springbootplayground.restdocs.domain.repository;

import com.kykapple.springbootplayground.restdocs.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
