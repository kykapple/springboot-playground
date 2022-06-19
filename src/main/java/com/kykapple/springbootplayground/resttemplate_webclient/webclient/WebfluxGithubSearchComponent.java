package com.kykapple.springbootplayground.resttemplate_webclient.webclient;

import com.kykapple.springbootplayground.resttemplate_webclient.aop.TimeMeasurement;
import com.kykapple.springbootplayground.resttemplate_webclient.dto.CountDto;
import com.kykapple.springbootplayground.resttemplate_webclient.dto.RepositoryCountDto;
import com.kykapple.springbootplayground.resttemplate_webclient.exception.GithubSearchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.CountDownLatch;

@Component
public class WebfluxGithubSearchComponent {

    private static final int REQUEST_COUNT = 4;
    private static final String BASE_URL = "https://api.github.com";

    private WebClient webClient;
    private String accessToken;

    public WebfluxGithubSearchComponent(
            WebClient webClient,
            @Value("${github.accessToken}") String accessToken
    ) {
        this.webClient = webClient;
        this.accessToken = accessToken;
    }

    @TimeMeasurement
    public void requestGithubProfile(String userName, Map<String, Integer> map) {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(REQUEST_COUNT);

            requestGithubRepositoryCount(userName, map, countDownLatch);
            requestGithubActivityCount(BASE_URL + "/search/issues?q=author:" + userName + "+type:issue", "ISSUE", map, countDownLatch);
            requestGithubActivityCount(BASE_URL + "/search/issues?q=author:" + userName + "+type:pr", "PR", map, countDownLatch);
            requestGithubActivityCount(BASE_URL + "/search/commits?q=author:" + userName, "COMMIT", map, countDownLatch);

            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new GithubSearchException();
        }
    }

    public void requestGithubActivityCount(String url, String type, Map<String, Integer> map, CountDownLatch countDownLatch) {
        requestGithubAPI(url)
                .bodyToMono(CountDto.class)
                .subscribe(countDto -> {
                    map.put(type, countDto.getCount());
                    countDownLatch.countDown();
                });
    }

    public void requestGithubRepositoryCount(String userName, Map<String, Integer> map, CountDownLatch countDownLatch) {
        requestGithubAPI(BASE_URL + "/users/" + userName)
                .bodyToMono(RepositoryCountDto.class)
                .subscribe(repositoryCountDto -> {
                    map.put("REPO", repositoryCountDto.getTotalRepos());
                    countDownLatch.countDown();
                });
    }

    public ResponseSpec requestGithubAPI(String url) {
        return webClient.get()
                .uri(url)
                .headers(httpHeaders -> {
                    httpHeaders.setBearerAuth(accessToken);
                    httpHeaders.set("Accept", "application/vnd.github.cloak-preview");
                })
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new GithubSearchException()));
    }

}
