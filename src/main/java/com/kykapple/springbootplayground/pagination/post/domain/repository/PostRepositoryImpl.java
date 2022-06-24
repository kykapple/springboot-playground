package com.kykapple.springbootplayground.pagination.post.domain.repository;

import com.kykapple.springbootplayground.pagination.post.domain.Post;
import com.kykapple.springbootplayground.pagination.post.domain.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PostRepositoryImpl implements PostCustomRepository {

    private JPAQueryFactory factory;

    public PostRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.factory = jpaQueryFactory;
    }

    @Override
    public List<Post> findPostsBetweenDate(LocalDateTime start, LocalDateTime end) {
        QPost post = QPost.post;

        return factory.selectFrom(post)
                .where(post.createdAt.between(start, end))
                .fetch();
    }

    @Override
    public Optional<Post> findPostByWriterName(String writer) {
        QPost post = QPost.post;

        return Optional.ofNullable(
                factory.selectFrom(post)
                        .where(post.writer.eq(writer))
                        .fetchFirst()
        );
    }
}
