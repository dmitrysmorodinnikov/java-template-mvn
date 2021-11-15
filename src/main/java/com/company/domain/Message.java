package com.company.domain;

import lombok.Getter;

//@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class Message extends Base {
    private String text;
    private User author;
    private Chat chat;
    private String createdAt;

    public Message(String text) {
        this.text = text;
    }
}


