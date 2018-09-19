package edu.palevobot.dao;

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
