package com.company.dto;

public class UserDTOFactory {
    public static UserDTO create(int id, String name) {
        return new UserDTO(id, name);
    }
}
