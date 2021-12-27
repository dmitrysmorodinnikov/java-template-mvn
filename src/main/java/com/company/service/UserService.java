package com.company.service;


import com.company.domain.User;
import com.company.dto.UserDTO;
import com.company.exceptions.MyAppException;

import java.util.List;

public interface UserService {
    void Create(User user);

    List<UserDTO> Get() throws MyAppException;
}
