package edu.palevobot.dao;

import edu.palevobot.config.BotConfig;
import edu.palevobot.entities.Palevo;
import edu.palevobot.entities.User;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PalevoJdbcDao extends JdbcDao<Palevo> {
    private final String INSERT_NEW = "INSERT INTO palevos(date_of_creation, title, description, subject, rating, document) " +
            "VALUES(?,?,?,?,?,?)";
    private final String GET_BY_ID = "SELECT * FROM palevos WHERE id=?";
    private final String UPDATE = "UPDATE palevos SET date_of_creation=?, title=?" +
            ", description=?, subject=?, rating=?, document=? WHERE id=?";
    private final String DELETE = "DELETE FROM palevos where id=?";
    private final String SELECT_ALL = "SELECT * FROM palevos";
    public PalevoJdbcDao() throws SQLException {
        super();
    }

    @Override
    public void insert(Palevo entity) throws SQLException, IOException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setDate(1, entity.getDateOfCreation());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setString(4, entity.getSubject());
            preparedStatement.setDouble(5, entity.getRating());
            preparedStatement.setString(6, entity.getDocument());
            preparedStatement.execute();
            closePreparedStatement();
        }
    }

    @Override
    public Palevo getById(int id) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int palevoId = resultSet.getInt(1);
                Date dateOfCreation = resultSet.getDate(2);
                String title = resultSet.getString(3);
                String description = resultSet.getString(4);
                String subject = resultSet.getString(5);
                double rating = resultSet.getDouble(6);
                String document = resultSet.getString(7);
                return new Palevo(palevoId, dateOfCreation, title, description
                        , document, rating);
            }
            closePreparedStatement();
        }
        return null;
    }

    @Override
    public void update(Palevo entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setDate(1, entity.getDateOfCreation());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setString(4, entity.getSubject());
            preparedStatement.setDouble(5, entity.getRating());
            preparedStatement.setString(6, entity.getDocument());
            preparedStatement.setInt(7, entity.getId());
            preparedStatement.execute();
            closePreparedStatement();
        }
    }

    @Override
    public void delete(Palevo entity) throws SQLException {
        if(connection != null) {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
            closePreparedStatement();
        }
    }

    @Override
    public ArrayList<Palevo> getAll() throws SQLException {
        ArrayList<Palevo> palevos = new ArrayList<>();
        if(connection != null) {
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int palevoId = resultSet.getInt(1);
                Date dateOfCreation = resultSet.getDate(2);
                String title = resultSet.getString(3);
                String description = resultSet.getString(4);
                String subject = resultSet.getString(5);
                double rating = resultSet.getDouble(6);
                String document = resultSet.getString(7);
                palevos.add(new Palevo(palevoId, dateOfCreation, title, description
                        , document, rating));
            }
            closePreparedStatement();
        }
        return palevos;
    }
}
