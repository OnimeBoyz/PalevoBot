package edu.palevobot.dao.palevo;

import edu.palevobot.dao.NoSqlDao;
import edu.palevobot.entities.Palevo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PalevoNoSQLDao extends NoSqlDao<Palevo> {
    @Override
    public void insert(Palevo entity) throws SQLException, IOException {
        //TODO
    }

    @Override
    public Palevo getById(int id) throws SQLException {
        //TODO
        return null;
    }

    @Override
    public void update(Palevo entity) throws SQLException {
        //TODO
    }

    @Override
    public void delete(Palevo entity) throws SQLException {
        //TODO
    }

    @Override
    public ArrayList<Palevo> getAll() throws SQLException {
        //TODO
        return null;
    }
}
