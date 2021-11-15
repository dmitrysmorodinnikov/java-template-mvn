package com.company.controller;

import com.company.domain.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/messages")
public class MessageController {

//    private final ChatService chatService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Message message) {
//        chatService.Create(chat);
        return ResponseEntity.ok().body(new HttpHeaders());
    }

//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity send(@RequestBody Chat chat) {
//        chatService.Create(chat);
//        return ResponseEntity.ok().body(new HttpHeaders());
//    }
}
