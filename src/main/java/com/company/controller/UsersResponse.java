package com.company.controller;

import com.company.dto.UserDTO;
import lombok.Getter;

import java.util.Collection;

/**
 * Represents HTTP response for GET getAll() endpoint in UserController
 */
@Getter
class UsersResponse extends BaseResponse {
    private Collection<UserDTO> users;

    UsersResponse(Collection<UserDTO> users, Collection<Error> errors) {
        super(errors);
        this.users = users;
    }
}
