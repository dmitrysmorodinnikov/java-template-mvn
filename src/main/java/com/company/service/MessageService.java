package com.company.service;

import com.company.domain.Message;

import java.util.List;

public interface MessageService {
    void Send(Message msg);
    void Read(Message msg);
    List<Message> getMessages(String chatId);
//    List<Message> GetAll(String userId);
//    List<Message> GetAllUnread(String userId);
}
