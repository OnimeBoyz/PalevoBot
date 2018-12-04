package edu.palevobot.dao.user;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.entities.User;

import javax.xml.transform.Result;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao extends JdbcDao<User> {

    private final String INSERT_NEW = "INSERT INTO users(username, rating, date_of_creation) VALUES(?, ?, ?)";
    private final String GET_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String GET_BY_USERNAME = "SELECT * FROM users WHERE username=?";
    private final String UPDATE = "UPDATE users SET username=?, rating=?, date_of_creation=? WHERE id=?";
    private final String DELETE = "DELETE FROM users where id=?";
    private final String SELECT_ALL = "SELECT * FROM users";
    private final String TRUNCATE = "Truncate table users";

    public UserJdbcDao() throws SQLException {
        super();
    }
    @Override
    public void insert(User entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setDouble(2, entity.getRating());
            preparedStatement.setDate(3, entity.getDateOfCreation());
            preparedStatement.execute();
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getUserFromResult(resultSet);
        }
        return null;
    }

    public User getByUsername(String username) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getUserFromResult(resultSet);
        }
        return null;
    }

    public User getUserFromResult(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int UserId = resultSet.getInt(1);
            String UserUsername = resultSet.getString(2);
            Double rating = resultSet.getDouble(3);
            Date dateOfCreation = resultSet.getDate(4);
            return new User(UserId, dateOfCreation, UserUsername);
        }
        return null;
    }
    @Override
    public void update(User entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setDouble(2, entity.getRating());
            preparedStatement.setDate(3, entity.getDateOfCreation());
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
    public List<User> getAll() throws SQLException {
        List<User> res = new ArrayList<>();
        if(connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String userUsername = resultSet.getString(2);
                double rating = resultSet.getDouble(3);
                Date userDateOfCreation = resultSet.getDate(4);
                res.add(new User(userId, userDateOfCreation, userUsername, rating));
            }
        }
        return res;
    }

    @Override
    public void truncate() throws SQLException {
        if(connection != null && !connection.isClosed()){
            preparedStatement = connection.prepareStatement(TRUNCATE);
            preparedStatement.executeUpdate();
        }
    }
}
