package edu.palevobot.dao.userpalevo;

import com.mongodb.client.MongoCollection;
import edu.palevobot.dao.NoSqlDao;
import edu.palevobot.entities.UserPalevo;
import org.bson.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPalevoNoSqlDao extends NoSqlDao<UserPalevo> {

    MongoCollection<Document> userPalevos;

    public UserPalevoNoSqlDao(){
        super();
        if(mongoClient != null){
            userPalevos = mongoDatabase.getCollection("userPalevos");
        }
    }

    @Override
    public void insert(UserPalevo entity) throws SQLException, IOException {
        //TODO
    }

    @Override
    public UserPalevo getById(int id) throws SQLException {
        //TODO
        return null;
    }

    @Override
    public void update(UserPalevo entity) throws SQLException {
        //TODO
    }

    @Override
    public void delete(UserPalevo entity) throws SQLException {
        //TODO
    }

    @Override
    public List<UserPalevo> getAll() throws SQLException {
        //TODO
        return null;
    }

    @Override
    public void drop(){
        if(mongoClient != null){
            userPalevos.drop();
        }
    }
}
