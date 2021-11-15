package com.company.service;

import com.company.domain.Chat;
import com.company.domain.Message;

import java.util.List;

public interface ChatService {
    void Create(Chat chat);
    void Send(Message msg);
    List<Message> GetMessages(long chatId);
}
