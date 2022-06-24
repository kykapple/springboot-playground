package com.kykapple.springbootplayground.pagination.post.domain.repository;

import com.kykapple.springbootplayground.pagination.post.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

    @Query("select p from Post p order by p.createdAt")
    List<Post> findAllPosts(Pageable pageable);

}
