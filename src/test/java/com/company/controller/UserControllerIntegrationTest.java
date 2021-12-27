package com.company.controller;

import com.company.App;
import com.company.domain.User;
import com.company.dto.UserDTO;
import com.company.dto.UserDTOFactory;
import com.company.service.AuthService;
import com.company.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class UserControllerIntegrationTest {

    @Autowired
    private Environment environment;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserController userController;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setup() throws SQLException {
        mvc = standaloneSetup(userController)
                .setControllerAdvice(new MyAppExceptionHandler())
                .build();
        clearDatabase();
    }

    @After
    public void teardown() throws SQLException {
        clearDatabase();
    }

    @Test
    public void should_return_users_ok() throws Exception {
        UserDTO userDTO1 = UserDTOFactory.create(1, "John");
        UserDTO userDTO2 = UserDTOFactory.create(2, "Leo");
        List<UserDTO> userDTOs = Arrays.asList(userDTO1, userDTO2);

        insertUser(new User(userDTO1.getId(), userDTO1.getName()));
        insertUser(new User(userDTO2.getId(), userDTO2.getName()));

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

    @Test
    public void when_not_authorized_should_return_forbidden_response() throws Exception {
        ForbiddenResponse expectedResponse = ResponseFactory.createForbiddenResponse();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String responseJsonString = ow.writeValueAsString(expectedResponse);

        mvc.perform(
                        get("/v1/users")
                                .header("Client-Id", null)
                                .header("Pass-Key", "passkey")
                )
                .andExpect(status().isForbidden())
                .andExpect(content().json(responseJsonString));
    }

    void insertUser(User user) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        final String dbUrl = environment.getProperty("spring.datasource.url");
        String sql = "INSERT INTO users (id, name) VALUES (?,?)";
        try (
                Connection connection = dataSource.getConnection()
//                Connection connection = DriverManager.getConnection(dbUrl,
//                environment.getProperty("spring.datasource.username"),
//                environment.getProperty("spring.datasource.password"))

        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.execute();
        }
    }

    void clearDatabase() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE users");
        ) {
            preparedStatement.execute();
        }
    }
}
