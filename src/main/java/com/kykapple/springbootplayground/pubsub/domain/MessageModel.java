package com.kykapple.springbootplayground.pubsub.domain;

import lombok.Getter;

@Getter
public class MessageModel {

    private String title;
    private String contents;

    public MessageModel() {
    }

    public MessageModel(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "MessageModel{ " +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                " }";
    }
}
