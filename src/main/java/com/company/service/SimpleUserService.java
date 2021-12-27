package com.company.service;

import com.company.domain.User;
import com.company.dto.UserDTO;
import com.company.dto.UserDTOFactory;
import com.company.exceptions.MyAppException;
import com.company.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@AllArgsConstructor
@Service
public class SimpleUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void Create(User user) {
        userRepository.Create(user);
    }

    @Override
    public List<UserDTO> Get() throws MyAppException {
        try {
            return toUserDTOs(userRepository.GetAll());
        } catch (MyAppException e) {
            throw e;
        } catch (Exception e) {
            MyAppException me = new MyAppException("error occurred when fetching users", e);
            throw me;
        }
    }

    private List<UserDTO> toUserDTOs(List<User> users) {
        return users.stream().map(this::toUserDTO).collect(Collectors.toList());
    }

    private UserDTO toUserDTO(User user) {
        return UserDTOFactory.create(user.getId(), user.getName());
    }
}
