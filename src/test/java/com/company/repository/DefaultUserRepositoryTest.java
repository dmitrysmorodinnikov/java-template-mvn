package com.company.repository;

import com.company.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DefaultUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setup() throws SQLException {
        // create table, create users
        createTable("users");
        insertUser(new User(1, "John"));
        insertUser(new User(2, "Leo"));
    }

    @AfterEach
    void teardown() throws SQLException {
        dropTable("users");
    }

    @Test
    public void test_get_all_should_fetch_users() {
        List<User> users = userRepository.GetAll();
        assertThat(users).usingFieldByFieldElementComparator().containsExactly(
                new User(1, "John"),
                new User(2, "Leo")
        );
    }

    void createTable(String tableName) throws SQLException {
        String sql = "CREATE TABLE %s (\n" +
                "    id int NOT NULL,\n" +
                "    name text NOT NULL,\n" +
                "    CONSTRAINT users_pkey PRIMARY KEY (id)\n" +
                ");";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(sql, tableName))) {
            preparedStatement.execute();
        }
    }

    void dropTable(String tableName) throws SQLException {
        String sql = "DROP TABLE %s ;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(sql, tableName))) {
            preparedStatement.execute();
        }
    }

    void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (id, name) VALUES (?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.execute();
        }
    }
}
