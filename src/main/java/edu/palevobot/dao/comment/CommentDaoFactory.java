package edu.palevobot.dao.comment;

import edu.palevobot.dao.Dao;
import edu.palevobot.dao.DaoFactory;
import edu.palevobot.dao.user.DaoType;

import java.sql.SQLException;

public class CommentDaoFactory extends DaoFactory {
    @Override
    public Dao getDao(DaoType type) throws SQLException {
        switch (type) {
            case SQL:
                return new CommentJdbcDao();
            case NoSQL:
                return new CommentNoSqlDao();
        }
        return null;
    }
}
