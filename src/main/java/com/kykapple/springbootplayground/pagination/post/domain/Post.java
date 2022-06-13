package com.kykapple.springbootplayground.pagination.post.domain;

import com.kykapple.springbootplayground.pagination.comment.domain.Comment;
import com.kykapple.springbootplayground.pagination.tag.domain.Tag;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;

    private String contents;

    @OneToMany(
            mappedBy = "post",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<PostTag> tags = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    public Post() {
    }

    @Builder
    public Post(String writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }

    public void addTags(List<Tag> tags) {
        this.tags.addAll(tags.stream()
                .map(tag -> new PostTag(this, tag))
                .collect(Collectors.toList())
        );
    }

}
