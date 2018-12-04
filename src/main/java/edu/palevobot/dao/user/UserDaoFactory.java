package edu.palevobot.dao.user;

import edu.palevobot.dao.Dao;
import edu.palevobot.dao.DaoFactory;
import edu.palevobot.entities.User;

import java.sql.SQLException;

public class UserDaoFactory extends DaoFactory<User> {
    @Override
    public Dao<User> getDao(DaoType type) throws SQLException {
        switch (type){
            case SQL:
                return new UserJdbcDao();
            case NoSQL:
                return new UserNoSqlDao();
        }
        return null;
    }
}
