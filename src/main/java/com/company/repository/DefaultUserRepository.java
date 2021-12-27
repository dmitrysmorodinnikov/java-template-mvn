package com.company.repository;

import com.company.domain.User;
import com.company.exceptions.MyAppException;
import org.postgresql.util.PSQLState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class DefaultUserRepository implements UserRepository {

    @Autowired
    private DataSource dataSource;

    private final String SQL_TEMPLATE = "SELECT id, name FROM users";

//    public DefaultUserRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Override
    public void Create(User user) {
    }

    @Override
    public List<User> GetAll() throws MyAppException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_TEMPLATE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(parseResultRow(resultSet));
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals(PSQLState.UNDEFINED_TABLE.getState())) {
                throw new MyAppException("the table is not found", e);
            } else {
                throw new MyAppException("error occurred while fetching users", e);
            }
        } catch (Exception e) {
            throw new MyAppException("error occurred while fetching users", e);
        }
        return users;
    }

    private User parseResultRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new User(id, name);
    }
}
