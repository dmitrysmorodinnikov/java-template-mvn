package com.company.service;

import com.company.domain.Chat;
import com.company.domain.Message;
import com.company.repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SimpleChatService implements ChatService{

    private final ChatRepository chatRepository;

    @Override
    public void Create(Chat chat) {
        chatRepository.Create(chat);
    }

    @Override
    public void Send(Message msg) {
        chatRepository.AddMessage(msg);
    }

    @Override
    public List<Message> GetMessages(long chatId) {
        return chatRepository.GetMessages(chatId);
    }
}
