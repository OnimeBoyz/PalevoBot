package edu.palevobot.entities;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Date;
import java.sql.SQLException;

public class Comment extends Base {
    private int palevoId;

    private String content;

    public Comment(int palevoId, String content){
        super();
        this.content = content;
        this.palevoId = palevoId;
    }
    public Comment(Palevo palevo, String content){
        super();
        this.content = content;
        this.palevoId = palevo.getId();
    }

    public Comment(Date dateOfCreation, int palevoId, String content){
        super(dateOfCreation);
        this.content = content;
        this.palevoId = palevoId;
    }
    public Comment(Date dateOfCreation, Palevo palevo, String content){
        super(dateOfCreation);
        this.content = content;
        this.palevoId = palevo.getId();
    }

    public Comment(int id, Date dateOfCreation, int palevoId, String content) {
        super(id, dateOfCreation);
        this.palevoId = palevoId;
        this.content = content;
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
