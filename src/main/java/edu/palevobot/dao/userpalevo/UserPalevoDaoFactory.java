package edu.palevobot.dao.userpalevo;

import edu.palevobot.dao.Dao;
import edu.palevobot.dao.DaoFactory;
import edu.palevobot.dao.user.DaoType;

import java.sql.SQLException;

public class UserPalevoDaoFactory extends DaoFactory {
    @Override
    public Dao getDao(DaoType type) throws SQLException {
        switch (type) {
            case SQL:
                return new UserPalevoJdbcDao();
            case NoSQL:
                return new UserPalevoNoSqlDao();
        }
        return null;
    }
}
