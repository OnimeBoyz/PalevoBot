package edu.palevobot.entities;

import java.sql.Date;

public class Base {
    private int id;
    private Date dateOfCreation;

    public Base(){
        this.dateOfCreation = new Date(System.currentTimeMillis());
    }

    public Base(Date dateOfCreation){this.dateOfCreation = dateOfCreation; }
    public Base(int id){
        this();
        this.id = id;
    }

    public Base(int id, Date dateOfCreation) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
    }

    public int getId() {
        return id;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }
}
