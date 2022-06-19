package com.kykapple.springbootplayground.resttemplate_webclient;

import com.kykapple.springbootplayground.resttemplate_webclient.webclient.GithubSearchComponent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class GithubSearchController {

    private GithubSearchComponent githubSearchComponent;

    public GithubSearchController(GithubSearchComponent githubSearchComponent) {
        this.githubSearchComponent = githubSearchComponent;
    }

    @GetMapping("/api/users/{userName}/profile")
    public ResponseEntity<Map<String, Integer>> searchGithubProfile(@PathVariable String userName) {
        Map<String, Integer> profileMap = new HashMap<>();
        githubSearchComponent.requestGithubProfile(userName, profileMap);

        return ResponseEntity.ok(profileMap);
    }

}
