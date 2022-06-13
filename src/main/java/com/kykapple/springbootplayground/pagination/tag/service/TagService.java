package com.kykapple.springbootplayground.pagination.tag.service;

import com.kykapple.springbootplayground.pagination.tag.domain.Tag;
import com.kykapple.springbootplayground.pagination.tag.domain.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class TagService {

    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> addTags(List<String> tags) {
        return tags.stream()
                .map(tagName ->
                        tagRepository.findByName(tagName)
                                .orElseGet(() -> tagRepository.save(new Tag(tagName)))
                )
                .collect(Collectors.toList());
    }

}
