package com.kykapple.springbootplayground.resttemplate_webclient.exception;

public class GithubSearchException extends RuntimeException {

    public GithubSearchException() {
        super("Github 조회 실패");
    }

}
