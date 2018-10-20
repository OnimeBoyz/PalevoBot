package edu.palevobot.dao.userpalevo;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.entities.Palevo;
import edu.palevobot.entities.User;
import edu.palevobot.entities.UserPalevo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserPalevoJdbcDao extends JdbcDao<UserPalevo> {
    private final String INSERT_NEW = "INSERT INTO user_palevos(user_id, palevo_id) VALUES(?,?)";
    private final String GET_BY_ID = "SELECT * FROM user_palevos WHERE id=?";
    private final String UPDATE = "UPDATE user_palevos SET user_id=?, palevo_id=? WHERE id=?";
    private final String DELETE = "DELETE FROM user_palevos where id=?";
    private final String SELECT_ALL = "SELECT * FROM users";

    public UserPalevoJdbcDao() throws SQLException {
        super();
    }

    @Override
    public void insert(UserPalevo entity) throws SQLException, IOException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getPalevoId());
            preparedStatement.execute();
        }
    }

    @Override
    public UserPalevo getById(int id) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int userPalevoId = resultSet.getInt(1);
                int user_id = resultSet.getInt(2);
                int palevo_id = resultSet.getInt(3);

                return new UserPalevo(userPalevoId, User.getById(user_id), Palevo.getById(id));
            }
        }
        return null;
    }

    @Override
    public void update(UserPalevo entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getPalevoId());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(UserPalevo entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
        }
    }

    @Override
    public ArrayList<UserPalevo> getAll() throws SQLException {
        ArrayList<UserPalevo> res = new ArrayList<>();
        if(connection != null) {
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userPalevoId = resultSet.getInt(1);
                int user_id = resultSet.getInt(2);
                int palevo_id = resultSet.getInt(3);
                res.add(new UserPalevo(userPalevoId, User.getById(user_id), Palevo.getById(palevo_id)));
            }
        }
        return res;
    }
}
