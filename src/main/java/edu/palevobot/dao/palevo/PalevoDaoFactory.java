package edu.palevobot.dao.palevo;

import edu.palevobot.dao.Dao;
import edu.palevobot.dao.DaoFactory;
import edu.palevobot.dao.user.DaoType;
import edu.palevobot.entities.Palevo;

import java.sql.SQLException;

public class PalevoDaoFactory extends DaoFactory<Palevo> {
    @Override
    public Dao<Palevo> getDao(DaoType type) throws SQLException {
        switch (type){
            case SQL:
                return new PalevoJdbcDao();
            case NoSQL:
                return new PalevoNoSQLDao();
        }
        return null;
    }
}
