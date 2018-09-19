package edu.palevobot.entities;

import java.sql.Date;

public class Base {
    private int id;
    private Date dateOfCreation;
    public Base(int id, Date dateOfCreation) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
    }

    public Base(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }
}
