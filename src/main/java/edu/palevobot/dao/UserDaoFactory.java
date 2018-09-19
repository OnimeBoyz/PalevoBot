package edu.palevobot.dao;

import edu.palevobot.entities.User;

import java.sql.SQLException;

public class UserDaoFactory extends DaoFactory<User> {
    @Override
    public Dao<User> getDao(String type) throws SQLException {
        //Передаем тип реализации как параметр(Странно...)
        switch (type){
            case "jdbc":
                return new UserJdbcDao();
        }
        return null;
    }
}
