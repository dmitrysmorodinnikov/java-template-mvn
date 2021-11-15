package com.company.repository;

import com.company.domain.Chat;
import com.company.domain.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryChatRepository implements ChatRepository{

    List<Chat> chats = new ArrayList<>();

    @Override
    public void Create(Chat chat) {
        chats.add(chat);
    }

    @Override
    public void AddMessage(Message msg) {
        if(msg != null && msg.getChat() != null) {
            msg.getChat().getMessages().add(msg);
        }
    }

    @Override
    public List<Message> GetMessages(long chatId) {
        Chat chat = getChatByID(chatId);
        return chat.getMessages();
    }

    private Chat getChatByID(long chatId) {
        return new Chat(chatId, "");
    }
}
