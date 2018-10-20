package edu.palevobot.dao.palevo;

import com.mongodb.client.MongoCollection;
import edu.palevobot.dao.NoSqlDao;
import edu.palevobot.entities.Palevo;
import org.bson.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PalevoNoSQLDao extends NoSqlDao<Palevo> {

    private MongoCollection<Document> palevos;

    public PalevoNoSQLDao(){
        super();
        if(mongoClient != null){
            palevos = mongoDatabase.getCollection("palevos");
        }
    }

    @Override
    public void insert(Palevo entity) throws SQLException, IOException {
        //TODO
    }

    @Override
    public Palevo getById(int id) throws SQLException {
        //TODO
        return null;
    }

    @Override
    public void update(Palevo entity) throws SQLException {
        //TODO
    }

    @Override
    public void delete(Palevo entity) throws SQLException {
        //TODO
    }

    @Override
    public ArrayList<Palevo> getAll() throws SQLException {
        //TODO
        return null;
    }
}
