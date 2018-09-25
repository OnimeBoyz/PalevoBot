package edu.palevobot.dao.comment;

import edu.palevobot.dao.Dao;
import edu.palevobot.dao.DaoFactory;

import java.sql.SQLException;

public class CommentDaoFactory extends DaoFactory {
    @Override
    public Dao getDao(String type) throws SQLException {
        switch (type) {
            case "jdbc" :
                return new CommentJdbcDao();
            case "nosql" :
                return new CommentNoSqlDao();
        }
        return null;
    }
}
