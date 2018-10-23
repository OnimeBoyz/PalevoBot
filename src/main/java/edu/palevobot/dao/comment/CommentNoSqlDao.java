package edu.palevobot.dao.comment;

import com.mongodb.client.MongoCollection;
import edu.palevobot.dao.NoSqlDao;
import edu.palevobot.entities.Comment;
import org.bson.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentNoSqlDao extends NoSqlDao<Comment> {

    private MongoCollection<Document> comments;

    public CommentNoSqlDao(){
        super();
        if(mongoClient != null){
            comments = mongoDatabase.getCollection("comments");
        }
    }

    @Override
    public void insert(Comment entity) throws SQLException, IOException {
        //TODO
    }

    @Override
    public Comment getById(int id) throws SQLException {
        //TODO
        return null;
    }

    @Override
    public void update(Comment entity) throws SQLException {
        //TODO
    }

    @Override
    public void delete(Comment entity) throws SQLException {
        //TODO
    }

    @Override
    public List<Comment> getAll() throws SQLException {
        //TODO
        return null;
    }

    @Override
    public void drop(){
        if(mongoClient != null){
            comments.drop();
        }
    }
}
