package com.kykapple.springbootplayground.kafka.model;

import lombok.Getter;

@Getter
public class MessageModel {

    private String writer;
    private String contents;

    public MessageModel() {
    }

    public MessageModel(String writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "writer='" + writer + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
