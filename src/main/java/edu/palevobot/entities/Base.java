package edu.palevobot.entities;

import java.sql.Date;

public class Base {
    private int id;
    private Date dateOfCreation;
    public Base(int id, Date dateOfCreation) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
    }

    protected int getId() {
        return id;
    }

    protected Date getDateOfCreation() {
        return dateOfCreation;
    }
}
