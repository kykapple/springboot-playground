package com.kykapple.springbootplayground.pagination.comment.domain;

import com.kykapple.springbootplayground.pagination.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {
    }

    @Builder
    public Comment(Post post, String writer, String contents) {
        this.post = post;
        this.writer = writer;
        this.contents = contents;
    }

}