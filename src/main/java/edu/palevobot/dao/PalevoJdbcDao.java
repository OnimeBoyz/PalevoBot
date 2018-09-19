package edu.palevobot.dao;

import edu.palevobot.entities.Palevo;

import java.sql.SQLException;
import java.util.ArrayList;

public class PalevoJdbcDao extends JdbcDao<Palevo> {
    private final String INSERT_NEW = "INSERT INTO (username) VALUES(?)";
    private final String GET_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String GET_BY_USERNAME = "SELECT * FROM users WHERE username=?";
    private final String UPDATE = "UPDATE users SET username=?, date_of_creation=?, rating=? WHERE id=?";
    private final String DELETE = "DELETE FROM users where id=?";
    private final String SELECT_ALL = "SELECT * FROM users";
    public PalevoJdbcDao() throws SQLException {
        super();
    }

    @Override
    public void insert(Palevo entity) throws SQLException {

    }

    @Override
    public Palevo getById(int id) throws SQLException {
        return null;
    }

    @Override
    public void update(Palevo entity) throws SQLException {

    }

    @Override
    public void delete(Palevo entity) throws SQLException {

    }

    @Override
    public ArrayList<Palevo> getAll() throws SQLException {
        return null;
    }
}
