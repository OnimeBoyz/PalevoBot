package edu.palevobot.entities;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Date;

public class Comment extends Base {
    private int userId;
    private int palevoId;

    private String content;
    public Comment(int id, Date dateOfCreation, int userId, int palevoId, String content) {
        super(id, dateOfCreation);
        this.userId = userId;
        this.palevoId = palevoId;
    }

    public int getPalevoId() {
        return palevoId;
    }

    public int getUserId() {
        return getPalevoId();
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
