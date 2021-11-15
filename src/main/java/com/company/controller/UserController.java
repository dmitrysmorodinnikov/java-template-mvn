package com.company.controller;

import com.company.domain.Chat;
import com.company.domain.User;
import com.company.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody User user) {
        userService.Create(user);
        return ResponseEntity.ok().body(new HttpHeaders());
    }

    @GetMapping(path = "/{id}/chats",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getChats(@PathVariable(value = "id") int id) {
//        List<Chat> chats = userService.GetChats(id);

        //todo: return chats
        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat(1, "chat with bob"));
        return ResponseEntity.ok(chats);
    }
}
