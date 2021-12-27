package com.company.controller;

import com.company.dto.UserDTO;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ResponseFactory {
    private static final String FORBIDDEN_ERROR_MESSAGE = "Authorization failed";

    /**
     * @return a body for 403 Forbidden Response
     */
    static ForbiddenResponse createForbiddenResponse() {
        return new ForbiddenResponse(Collections.singletonList(new Error(FORBIDDEN_ERROR_MESSAGE)));
    }

    /**
     * @param errors
     * @return a body for 422 UnprocessableEntity response
     */
    static UnprocessableEntityResponse createUnprocessableEntityResponse(List<Error> errors) {
        return new UnprocessableEntityResponse(errors);
    }

    static UsersResponse createUsersResponse(Collection<UserDTO> userDTOs, Collection<Error> errors) {
        return new UsersResponse(userDTOs, errors);
    }
}
