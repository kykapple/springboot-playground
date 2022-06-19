package com.kykapple.springbootplayground.resttemplate_webclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RepositoryCountDto {

    @JsonProperty("public_repos")
    private int publicRepos;

    @JsonProperty("total_private_repos")
    private int privateRepos;

    public int getTotalRepos() {
        return publicRepos + privateRepos;
    }

}
