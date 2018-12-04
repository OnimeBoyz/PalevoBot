package edu.palevobot.entities;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.dao.user.UserDaoFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Base {
    private String username;
    private double rating;

    public static ArrayList<User> users = new ArrayList<>();

    public User(int id, Date dateOfCreation, String username) {
        super(id, dateOfCreation);
        this.username = username;
        this.rating = calculateRating();

        users.add(this);
    }

    public User(int id, String username) {
        super(id);
        this.username = username;

        users.add(this);
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

    public static User getById(int id) throws SQLException {
        for (User user : User.users) {
            if(user.getId() == id)
                return user;
        }
        JdbcDao jdbcDao = (JdbcDao) new UserDaoFactory().getDao("jdbc");
        User user = (User) jdbcDao.getById(id);
        return user;
    }

    @Override
    public String toString() {
        return getId() + " " + username + rating + "\n";
    }
}
