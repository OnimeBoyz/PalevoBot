package edu.palevobot.dao.userpalevo;

import edu.palevobot.dao.Dao;
import edu.palevobot.dao.DaoFactory;

import java.sql.SQLException;

public class UserPalevoDaoFactory extends DaoFactory {
    @Override
    public Dao getDao(String type) throws SQLException {
        switch (type) {
            case "jdbc" :
                return new UserPalevoJdbcDao();
            case "nosql" :
                return new UserPalevoNoSqlDao();
        }
        return null;
    }
}
