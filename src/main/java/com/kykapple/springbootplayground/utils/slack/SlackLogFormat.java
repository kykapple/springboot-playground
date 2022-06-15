package com.kykapple.springbootplayground.utils.slack;

import lombok.Getter;

@Getter
public class SlackLogFormat {

    private String username;
    private String text;

    public SlackLogFormat(String username, String text) {
        this.username = username;
        this.text = text;
    }

}
