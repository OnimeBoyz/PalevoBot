package edu.palevobot.dao.comment;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.entities.Comment;
import edu.palevobot.entities.Palevo;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentJdbcDao extends JdbcDao<Comment> {
    private final String INSERT_NEW = "INSERT INTO comments(palevo_id, content, date_of_creation) " +
            "VALUES(?,?,?)";
    private final String GET_BY_ID = "SELECT * FROM comments WHERE id=?";
    private final String UPDATE = "UPDATE comments SET palevo_id=?, content=?" +
            ", date_of_creation=? WHERE id=?";
    private final String DELETE = "DELETE FROM comments where id=?";
    private final String SELECT_ALL = "SELECT * FROM comments";
    private final String TRUNCATE = "Truncate table comments";

    public CommentJdbcDao() throws SQLException {
        super();
    }

    @Override
    public void insert(Comment entity) throws SQLException, IOException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setInt(1, entity.getPalevoId());
            preparedStatement.setString(2, entity.getContent());
            preparedStatement.setDate(3, entity.getDateOfCreation());
            preparedStatement.execute();
            closePreparedStatement();
        }
    }

    @Override
    public Comment getById(int id) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int commentPalevoId = resultSet.getInt(2);
                String commentContent = resultSet.getString(3);
                Date commentDateOfCreation = resultSet.getDate(4);
                return new Comment(id, commentDateOfCreation, commentPalevoId, commentContent);
            }
            closePreparedStatement();
        }
        return null;
    }

    @Override
    public void update(Comment entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setDate(3, entity.getDateOfCreation());
            preparedStatement.setInt(1, entity.getPalevoId());
            preparedStatement.setString(2, entity.getContent());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.execute();
            closePreparedStatement();
        }
    }

    @Override
    public void delete(Comment entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
            closePreparedStatement();
        }
    }

    @Override
    public List<Comment> getAll() throws SQLException {
        List<Comment> comments = new ArrayList<>();
        if(connection != null) {
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int commentId = resultSet.getInt(1);
                int commentPalevoId = resultSet.getInt(2);
                String commentContent = resultSet.getString(3);
                Date commentDateOfCreation = resultSet.getDate(4);
                comments.add(new Comment(commentId, commentDateOfCreation, commentPalevoId, commentContent));
            }
            closePreparedStatement();
        }
        return comments;
    }

    @Override
    public void truncate() throws SQLException {
        if(connection != null && !connection.isClosed()){
            preparedStatement = connection.prepareStatement(TRUNCATE);
            preparedStatement.executeUpdate();
        }
    }
}
