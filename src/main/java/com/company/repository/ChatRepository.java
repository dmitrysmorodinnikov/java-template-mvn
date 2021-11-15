package com.company.repository;

import com.company.domain.Chat;
import com.company.domain.Message;

import java.util.List;

public interface ChatRepository {
    void Create(Chat user);
    void AddMessage(Message msg);
    List<Message> GetMessages(long chatId);
}
