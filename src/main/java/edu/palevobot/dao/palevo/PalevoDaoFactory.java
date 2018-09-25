package edu.palevobot.dao.palevo;

import edu.palevobot.dao.Dao;
import edu.palevobot.dao.DaoFactory;
import edu.palevobot.entities.Palevo;

import java.sql.SQLException;

public class PalevoDaoFactory extends DaoFactory<Palevo> {
    @Override
    public Dao<Palevo> getDao(String type) throws SQLException {
        switch (type){
            case "jdbc":
                return new PalevoJdbcDao();
        }
        return null;
    }
}
