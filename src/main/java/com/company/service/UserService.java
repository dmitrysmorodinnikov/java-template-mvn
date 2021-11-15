package com.company.service;


import com.company.domain.Chat;
import com.company.domain.User;

import java.util.List;

public interface UserService {
    void Create(User user);

    List<Chat> GetChats(long userId);
}
