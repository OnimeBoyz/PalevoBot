package edu.palevobot.dao.userpalevo;

import edu.palevobot.dao.NoSqlDao;
import edu.palevobot.entities.UserPalevo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserPalevoNoSqlDao extends NoSqlDao<UserPalevo> {
    @Override
    public void insert(UserPalevo entity) throws SQLException, IOException {
        //TODO
    }

    @Override
    public UserPalevo getById(int id) throws SQLException {
        //TODO
        return null;
    }

    @Override
    public void update(UserPalevo entity) throws SQLException {
        //TODO
    }

    @Override
    public void delete(UserPalevo entity) throws SQLException {
        //TODO
    }

    @Override
    public ArrayList<UserPalevo> getAll() throws SQLException {
        //TODO
        return null;
    }
}
