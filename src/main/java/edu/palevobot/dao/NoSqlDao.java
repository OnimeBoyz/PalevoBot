package edu.palevobot.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import edu.palevobot.config.DaoConstants;
import edu.palevobot.entities.Base;
import com.mongodb.*;
public abstract class NoSqlDao<T extends Base> extends Dao<T>{
    protected MongoClient mongoClient;
    protected MongoDatabase mongoDatabase;
    public NoSqlDao() {
        mongoClient = MongoClients.create(DaoConstants.NOSQL_MONGO_URL);
    }

    public void closeMongoClient() {
        if(mongoClient != null) {
            mongoClient.close();
        }
    }
}
