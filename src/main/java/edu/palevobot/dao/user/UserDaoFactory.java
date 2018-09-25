package edu.palevobot.dao.user;

import edu.palevobot.dao.Dao;
import edu.palevobot.dao.DaoFactory;
import edu.palevobot.entities.User;

import java.sql.SQLException;

public class UserDaoFactory extends DaoFactory<User> {
    @Override
    public Dao<User> getDao(String type) throws SQLException {
        //Передаем тип реализации как параметр(Странно...)
        switch (type){
            case "jdbc":
                return new UserJdbcDao();
            case "nosql":
                return new UserNoSqlDao();
        }
        return null;
    }
}
