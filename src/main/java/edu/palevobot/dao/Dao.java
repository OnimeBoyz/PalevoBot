package edu.palevobot.dao;

import edu.palevobot.entities.Base;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Dao<T extends Base> {
    public abstract void insert(T entity) throws SQLException;
    public abstract T getById(int id) throws SQLException;
    public abstract void update(T entity) throws SQLException;
    public abstract void delete(T entity) throws SQLException;
    public abstract ArrayList<T> getAll() throws SQLException;
}
