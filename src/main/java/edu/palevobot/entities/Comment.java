package edu.palevobot.entities;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Date;
import java.sql.SQLException;

public class Comment extends Base {
    private int userId;

    private String content;
    public Comment(int id, Date dateOfCreation, int userId, int palevoId, String content) {
        super(id, dateOfCreation);
        this.userId = userId;
    }


    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        try {
            return User.getById(userId).getUsername() + getDateOfCreation() + content;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
