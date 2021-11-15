package com.company.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Getter
@Setter
public class User {
    private String name;
//    private String email;
//    private List<Chat> chats;
}
