package edu.palevobot.rest;

import edu.palevobot.entities.Base;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

public interface IService<T extends Base> {
    Response getObject(int id) throws SQLException;
    Response removeObject(int id) throws SQLException;
    Response createObject(T obj) throws IOException, SQLException;
    Response updateObject(T obj) throws SQLException;
    Response getAll(String keyword, String orderBy, String order, int pageNum, int pageSize);
}
