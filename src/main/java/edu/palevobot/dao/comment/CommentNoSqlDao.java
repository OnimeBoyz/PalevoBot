package edu.palevobot.dao.comment;

import edu.palevobot.dao.NoSqlDao;
import edu.palevobot.entities.Comment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentNoSqlDao extends NoSqlDao<Comment> {
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
    public ArrayList<Comment> getAll() throws SQLException {
        //TODO
        return null;
    }
}
