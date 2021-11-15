package com.company.controller;

import com.company.domain.Chat;
import com.company.domain.Message;
import com.company.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
//@RequestMapping("/v1/chats")
public class ChatController {

    private final ChatService chatService;

    @PostMapping(path = "/v1/chats",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Chat chat) {
        chatService.Create(chat);
        return ResponseEntity.ok().body(new HttpHeaders());
    }

    @PostMapping(path = "/v1/chats/{id}/send",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity send(@RequestBody Message msg) {
        chatService.Send(msg);
        return ResponseEntity.ok().body(new HttpHeaders());
    }

    @GetMapping(path = "/v1/chats/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMessages(@PathVariable long id) {
        chatService.GetMessages(id);
        return ResponseEntity.ok().body(new HttpHeaders());
    }
}
