package com.company.controller;

import com.company.dto.UserDTO;
import com.company.exceptions.MyAppException;
import com.company.service.AuthService;
import com.company.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/v1/users")
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestHeader("Client-Id") String clientId,
                                 @RequestHeader("Pass-Key") String passKey) throws MyAppException {
        if (!authService.isAuthorized(clientId, passKey)) {
            LOGGER.error("Unathorized access attempt, clientId = {}, passKey = {}", clientId, passKey);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseFactory.createForbiddenResponse());
        }
        Collection<UserDTO> users = userService.Get();
        return ResponseEntity.ok(ResponseFactory.createUsersResponse(users, Collections.emptyList()));
    }

//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity create(@RequestBody User user) {
//        userService.Create(user);
//        return ResponseEntity.ok().body(new HttpHeaders());
//    }
}
