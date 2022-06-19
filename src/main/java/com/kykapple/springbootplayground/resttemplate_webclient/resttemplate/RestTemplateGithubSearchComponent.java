package com.kykapple.springbootplayground.resttemplate_webclient.resttemplate;

import com.kykapple.springbootplayground.resttemplate_webclient.aop.TimeMeasurement;
import com.kykapple.springbootplayground.resttemplate_webclient.dto.CountDto;
import com.kykapple.springbootplayground.resttemplate_webclient.dto.RepositoryCountDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestTemplateGithubSearchComponent {

    private static final String BASE_URL = "https://api.github.com";

    private String accessToken;

    public RestTemplateGithubSearchComponent(@Value("${github.accessToken}") String accessToken) {
        this.accessToken = accessToken;
    }

    @TimeMeasurement
    public void requestGithubProfile(String userName, Map<String, Integer> map) {
        requestGithubRepositoryCount(userName, map);
        requestGithubActivityCount(BASE_URL + "/search/issues?q=author:" + userName + "+type:issue", "ISSUE", map);
        requestGithubActivityCount(BASE_URL + "/search/issues?q=author:" + userName + "+type:pr", "PR", map);
        requestGithubActivityCount(BASE_URL + "/search/commits?q=author:" + userName, "COMMIT", map);
    }

    public void requestGithubActivityCount(String url, String type, Map<String, Integer> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer" + accessToken);
        headers.add("Accept", "application/vnd.github.cloak-preview");

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<CountDto> response = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                CountDto.class
        );

        map.put(type, response.getBody().getCount());
    }

    public void requestGithubRepositoryCount(String userName, Map<String, Integer> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer" + accessToken);
        headers.add("Accept", "application/vnd.github.cloak-preview");

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<RepositoryCountDto> response = new RestTemplate().exchange(
                BASE_URL + "/users/" + userName,
                HttpMethod.GET,
                httpEntity,
                RepositoryCountDto.class
        );

        map.put("REPO", response.getBody().getTotalRepos());
    }

}
