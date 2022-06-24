package com.kykapple.springbootplayground.pagination.post.domain;

import com.kykapple.springbootplayground.pagination.tag.domain.Tag;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Embeddable
public class PostTags {

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<PostTag> tags;

    public PostTags() {
        tags = new ArrayList<>();
    }

    public void addTags(Post post, List<Tag> tags) {
        this.tags.addAll(tags.stream()
                .map(tag -> new PostTag(post, tag))
                .collect(Collectors.toList())
        );
    }

}
