package edu.palevobot.entities;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Date;
import java.util.ArrayList;

public class User extends Base {
    private String username;
    private double rating;

    public static ArrayList<User> users = new ArrayList<>();

    public User(int id, Date dateOfCreation, String username) throws ExecutionControl.NotImplementedException {
        super(id, dateOfCreation);
        this.username = username;
        this.rating = calculateRating();

        users.add(this);
        //Добавить челикса в бд
        throw new ExecutionControl.NotImplementedException("Добавить юзера");
    }

    private double calculateRating() {
        return -1.0;
    }

    public double getRating() {
        return calculateRating();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
