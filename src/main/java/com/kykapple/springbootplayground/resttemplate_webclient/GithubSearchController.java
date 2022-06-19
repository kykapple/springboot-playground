package com.kykapple.springbootplayground.resttemplate_webclient;

import com.kykapple.springbootplayground.resttemplate_webclient.resttemplate.RestTemplateGithubSearchComponent;
import com.kykapple.springbootplayground.resttemplate_webclient.webclient.WebfluxGithubSearchComponent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class GithubSearchController {

    private WebfluxGithubSearchComponent webfluxGithubSearchComponent;
    private RestTemplateGithubSearchComponent restTemplateGithubSearchComponent;

    public GithubSearchController(
            WebfluxGithubSearchComponent webfluxGithubSearchComponent,
            RestTemplateGithubSearchComponent restTemplateGithubSearchComponent
    ) {
        this.webfluxGithubSearchComponent = webfluxGithubSearchComponent;
        this.restTemplateGithubSearchComponent = restTemplateGithubSearchComponent;
    }

    @GetMapping("/api/users/{userName}/profile/webflux")
    public ResponseEntity<Map<String, Integer>> searchGithubProfileWithWebflux(@PathVariable String userName) {
        Map<String, Integer> profileMap = new HashMap<>();
        webfluxGithubSearchComponent.requestGithubProfile(userName, profileMap);

        return ResponseEntity.ok(profileMap);
    }

    @GetMapping("/api/users/{userName}/profile/restTemplate")
    public ResponseEntity<Map<String, Integer>> searchGithubProfileWithRestTemplate(@PathVariable String userName) {
        Map<String, Integer> profileMap = new HashMap<>();
        restTemplateGithubSearchComponent.requestGithubProfile(userName, profileMap);

        return ResponseEntity.ok(profileMap);
    }

}
