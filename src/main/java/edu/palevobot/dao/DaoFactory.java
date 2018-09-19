package edu.palevobot.dao;

import edu.palevobot.entities.Base;

import java.sql.SQLException;

public abstract class DaoFactory<T extends Base> {
    public abstract Dao<T> getDao(String type) throws SQLException;
}