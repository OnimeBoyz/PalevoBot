package edu.palevobot.entities;

import jdk.jshell.spi.ExecutionControl;
import org.telegram.telegrambots.meta.api.objects.Document;

import java.sql.Date;
import java.util.ArrayList;

public class Palevo extends Base {
    private String title;
    private String description;
    //current
    private String subject;
    private double rating;
    private Document document;
    public static ArrayList<Palevo> palevos = new ArrayList<>();

    public Palevo(int id, Date dateOfCreation, String title, String description, Document document) throws ExecutionControl.NotImplementedException {
        super(id, dateOfCreation);
        this.title = title;
        this.description = description;
        this.rating = 0;
        this.document = document;

        palevos.add(this);
        //Добавить палево в БД
        throw new ExecutionControl.NotImplementedException("добавить палево в БД");
    }

    public Palevo(int id, Date dateOfCreation, String title, String description, Document document, double rating) throws ExecutionControl.NotImplementedException {
        super(id, dateOfCreation);
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.document = document;

        palevos.add(this);
        //Добавить палево в БД
        throw new ExecutionControl.NotImplementedException("добавить палево в БД");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    public double getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
