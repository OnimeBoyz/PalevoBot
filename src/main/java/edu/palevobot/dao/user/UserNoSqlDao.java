package edu.palevobot.dao.user;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import edu.palevobot.dao.NoSqlDao;
import edu.palevobot.entities.User;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;

public class UserNoSqlDao extends NoSqlDao<User> {

    private MongoCollection<Document> users;

    public UserNoSqlDao() {
        super();
        if(mongoClient != null) {
            users = mongoDatabase.getCollection("users");
        }
    }


    @Override
    public void insert(User entity) throws SQLException, IOException {
        if(users != null) {
            users.insertOne(toDocument(entity));
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        if(users != null) {
            return fromDocument(users.find(eq("id", id)).first());
        }
        return null;
    }

    @Override
    public void update(User entity) throws SQLException {
        if(users != null) {
            users.updateOne(eq("id", entity.getId()), toDocument(entity));
        }
    }

    @Override
    public void delete(User entity) throws SQLException {
        if(users != null) {
            users.deleteOne(eq("id", entity.getId()));
        }
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> res = new ArrayList<>();
        if(users != null) {
            Iterator it = users.find().iterator();
            while (it.hasNext()) {
                res.add(fromDocument((Document) it.next()));
            }
        }
        return res;
    }

    public User fromDocument(Document document) {
        return new User((int) document.get("id")
                , (String) document.get("username"));
    }

    public Document toDocument(User user) {
        Document doc = new Document("id", user.getId())
                .append("username", user.getUsername())
                .append("date_of_creation", user.getDateOfCreation())
                .append("rating", user.getRating());
        return doc;
    }
    @Override
    public void drop(){
        if(mongoClient != null){
            users.drop();
        }
    }
}
