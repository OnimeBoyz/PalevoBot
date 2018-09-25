package edu.palevobot.entities;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Date;
import java.sql.SQLException;

public class Comment extends Base {
    private int palevoId;

    private String content;
    public Comment(int id, Date dateOfCreation, int palevoId, String content) {
        super(id, dateOfCreation);
        this.palevoId = palevoId;
    }


    public int getPalevoId() {
        return palevoId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        try {
            return User.getById(palevoId).getUsername() + getDateOfCreation() + content;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
