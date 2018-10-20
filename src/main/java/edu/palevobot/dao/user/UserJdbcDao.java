package edu.palevobot.dao.user;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.entities.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserJdbcDao extends JdbcDao<User> {

    private final String INSERT_NEW = "INSERT INTO users(username) VALUES(?)";
    private final String GET_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String GET_BY_USERNAME = "SELECT * FROM users WHERE username=?";
    private final String UPDATE = "UPDATE users SET username=?, date_of_creation=?, rating=? WHERE id=?";
    private final String DELETE = "DELETE FROM users where id=?";
    private final String SELECT_ALL = "SELECT * FROM users";

    public UserJdbcDao() throws SQLException {
        super();
    }
    @Override
    public void insert(User entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.execute();
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int UserId = resultSet.getInt(1);
                String UserUsername = resultSet.getString(2);
                Date dateOfCreation = resultSet.getDate(3);
                return new User(UserId, dateOfCreation, UserUsername);
            }
        }
        return null;
    }

    public User getByUsername(String username) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int UserId = resultSet.getInt(1);
                String UserUsername = resultSet.getString(2);
                Date dateOfCreation = resultSet.getDate(3);
                return new User(UserId, dateOfCreation, UserUsername);
            }
        }
        return null;
    }

    @Override
    public void update(User entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setDate(2, entity.getDateOfCreation());
            preparedStatement.setDouble(3, entity.getRating());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(User entity) throws SQLException{
        if(connection != null) {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> res = new ArrayList<>();
        if(connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String userUsername = resultSet.getString(2);
                Date userDateOfCreation = resultSet.getDate(3);
                res.add(new User(userId, userDateOfCreation, userUsername));
            }
        }
        return res;
    }
}
