package com.company.service;

import com.company.domain.Chat;
import com.company.domain.User;
import com.company.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SimpleUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public void Create(User user) {
        userRepository.Create(user);
    }

    @Override
    public List<Chat> GetChats(long userId) {
        return null;
    }
}
