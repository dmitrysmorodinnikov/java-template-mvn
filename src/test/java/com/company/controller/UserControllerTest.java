package com.company.controller;

import com.company.App;
import com.company.dto.UserDTO;
import com.company.dto.UserDTOFactory;
import com.company.exceptions.MyAppException;
import com.company.service.AuthService;
import com.company.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        mvc = standaloneSetup(userController)
                .setControllerAdvice(new MyAppExceptionHandler())
                .build();
    }

    @Test
    public void when_not_authorized_should_return_forbidden_response() throws Exception {
        when(authService.isAuthorized(any(), any())).thenReturn(false);
        ForbiddenResponse expectedResponse = ResponseFactory.createForbiddenResponse();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String responseJsonString = ow.writeValueAsString(expectedResponse);

        mvc.perform(
                        get("/v1/users")
                                .header("Client-Id", "client_id")
                                .header("Pass-Key", "passkey")
                )
                .andExpect(status().isForbidden())
                .andExpect(content().json(responseJsonString));
    }

    @Test
    public void when_service_throws_exception_should_return_internal_error_response() throws Exception {
        when(authService.isAuthorized(any(), any())).thenReturn(true);
        when(userService.Get()).thenThrow(MyAppException.class);

        mvc.perform(
                        get("/v1/users")
                                .header("Client-Id", "client_id")
                                .header("Pass-Key", "passkey")
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void should_return_users_ok() throws Exception {
        UserDTO userDTO1 = UserDTOFactory.create(1, "John");
        UserDTO userDTO2 = UserDTOFactory.create(2, "Leo");
        List<UserDTO> userDTOs = Arrays.asList(userDTO1, userDTO2);

        when(authService.isAuthorized(any(), any())).thenReturn(true);
        when(userService.Get()).thenReturn(userDTOs);

        UsersResponse expectedResponse = ResponseFactory.createUsersResponse(userDTOs, Collections.emptyList());
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String responseJsonString = ow.writeValueAsString(expectedResponse);

        mvc.perform(
                        get("/v1/users")
                                .header("Client-Id", "client_id")
                                .header("Pass-Key", "passkey")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(responseJsonString));
    }
}
