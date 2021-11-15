package com.company.repository;

import com.company.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryUserRepository implements UserRepository{

    List<User> users = new ArrayList<>();

    @Override
    public void Create(User user) {
        if(user != null) {
            users.add(user);
        }
    }
}
