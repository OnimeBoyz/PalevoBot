package edu.palevobot.rest;

import edu.palevobot.dao.parameters.Order;
import edu.palevobot.dao.parameters.UserListParameters;
import edu.palevobot.dao.user.UserNoSqlDao;
import edu.palevobot.entities.User;
import edu.palevobot.exceptions.Error;
import edu.palevobot.rest.response.ResponseCreator;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UsersServiceJSON implements IService<User> {

    private UserNoSqlDao dao;

    public UserNoSqlDao getUserDao(){
        return dao;
    }

    public void setDao(UserNoSqlDao dao){
        this.dao = dao;
    }

    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion(){
        return requestHeaders.getRequestHeader("version").get(0);
    }

    @GET
    @Path("/{id}")
    @Override
    public Response getObject(@PathParam("id") int id) throws SQLException {
        User user = dao.getById(id);
        if(user != null)
            return ResponseCreator.
                    success(getHeaderVersion(), user);
        else
            return ResponseCreator.
                    error(404, Error.NOT_FOUND.getCode(), getHeaderVersion());
    }

    @DELETE
    @Path("/{id}")
    @Override
    public Response removeObject(@PathParam("id") int id) throws SQLException {
        User deleteUser = dao.getById(id);
        if(deleteUser != null){
            dao.delete(deleteUser);
            return ResponseCreator.
                    success(getHeaderVersion(), deleteUser.toString() + " is removed");
        }
        return ResponseCreator.
                error(500, Error.SERVER_ERROR.getCode(), getHeaderVersion());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response createObject(User obj) throws IOException, SQLException {
        dao.insert(obj);
        User createdUser = dao.getById(obj.getId());
        if( createdUser != null)
            return ResponseCreator.
                    success(getHeaderVersion(), createdUser);
        else
            return ResponseCreator.
                    error(500, Error.SERVER_ERROR.getCode(), getHeaderVersion());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response updateObject(User obj) throws SQLException {
        dao.update(obj);
        User updatedUser = dao.getById(obj.getId());
        if(updatedUser != null)
            return ResponseCreator.
                    success(getHeaderVersion(), updatedUser);
        else
            return ResponseCreator.
                    error(404, Error.NOT_FOUND.getCode(), getHeaderVersion());
    }

    //TODO: do this
    @GET
    @Override
    public Response getAll() throws SQLException {
        List<User> userList = dao.getAll();
        if(userList != null)
        {
            GenericEntity<List<User>> entity = new GenericEntity<>(userList){};
            return ResponseCreator.success(getHeaderVersion(), entity);
        }
        else return ResponseCreator.
                error(404, Error.NOT_FOUND.getCode(), getHeaderVersion());
    }
}
