package com.company.repository;


import com.company.domain.User;
import com.company.exceptions.MyAppException;

import java.util.List;

public interface UserRepository {
    void Create(User user);
    List<User> GetAll() throws MyAppException;
}
