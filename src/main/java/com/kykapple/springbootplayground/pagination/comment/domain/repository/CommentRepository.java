package com.kykapple.springbootplayground.pagination.comment.domain.repository;

import com.kykapple.springbootplayground.pagination.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
