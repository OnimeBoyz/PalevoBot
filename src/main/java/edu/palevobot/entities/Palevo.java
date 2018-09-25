package edu.palevobot.entities;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.dao.palevo.PalevoDaoFactory;
import jdk.jshell.spi.ExecutionControl;
import org.telegram.telegrambots.meta.api.objects.Document;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Palevo extends Base {
    private String title;
    private String description;
    //current
    private String subject;
    private double rating;
    private String document;
    public static ArrayList<Palevo> palevos = new ArrayList<>();

    public Palevo(int id, Date dateOfCreation, String title, String description, String document) {
        super(id, dateOfCreation);
        this.title = title;
        this.description = description;
        this.rating = 0;
        this.document = document;

        palevos.add(this);
    }

    public Palevo(int id, Date dateOfCreation, String title, String description, String document, double rating){
        super(id, dateOfCreation);
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.document = document;

        palevos.add(this);
    }

    public Palevo(int id, String title, String description, String document, double rating){
        super(id);
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.document = document;

        palevos.add(this);
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

    public String getDocument() {
        return document;
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

    public static Palevo getById(int id) throws SQLException {
        for (Palevo palevo : Palevo.palevos) {
            if(palevo.getId() == id)
                return palevo;
        }
        JdbcDao jdbcDao = (JdbcDao) new PalevoDaoFactory().getDao("jdbc");
        Palevo palevo = (Palevo) jdbcDao.getById(id);
        jdbcDao.closeConnection();
        return (Palevo) palevo;
    }
}
