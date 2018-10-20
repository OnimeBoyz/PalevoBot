package edu.palevobot.dao;

import edu.palevobot.entities.Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import edu.palevobot.config.DaoConstants;

public abstract class JdbcDao<T extends Base> extends Dao<T> {

    protected PreparedStatement preparedStatement;
    protected Connection connection;

    public JdbcDao() throws SQLException {
        connection = DriverManager.getConnection(DaoConstants.JDBC_URL,
                DaoConstants.USERNAME, DaoConstants.PASSWORD);
    }

    public abstract void truncate();

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void closePreparedStatement() {
        if(preparedStatement != null) {
            try {
                preparedStatement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e){
                System.out.println(e.getStackTrace());
            }
        }
    }
}
