package edu.palevobot.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.palevobot.config.DaoConstants;
import edu.palevobot.entities.Base;
import com.mongodb.*;
import edu.palevobot.entities.Palevo;
import edu.palevobot.entities.User;
import edu.palevobot.entities.UserPalevo;
import org.bson.Document;

import java.util.ArrayList;

public abstract class NoSqlDao<T extends Base> extends Dao<T>{

    protected MongoClient mongoClient;
    protected MongoDatabase mongoDatabase;

    public NoSqlDao() {
        mongoClient = MongoClients.create(DaoConstants.NOSQL_MONGO_URL);
        if(mongoClient != null){
            mongoDatabase = mongoClient.getDatabase("palevobotdb");
        }
    }

    @Override
    public void closeConnection(){
                if(mongoClient != null) {
            mongoClient.close();
        }
    }
}
