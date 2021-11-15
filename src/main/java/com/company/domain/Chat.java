package com.company.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Chat {
    long id;
    private String name;
    private List<User> members;
    List<Message> messages;
    private String createdAt;

    public Chat(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
