package com.kykapple.springbootplayground.exception.dto;

import lombok.Getter;

@Getter
public class ExceptionHandleResponse {

    private int status;
    private String message;

    public ExceptionHandleResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
