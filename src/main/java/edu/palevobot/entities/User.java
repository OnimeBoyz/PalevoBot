package edu.palevobot.entities;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.dao.user.DaoType;
import edu.palevobot.dao.user.UserDaoFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Base {
    private String username;
    private double rating;

    public static ArrayList<User> users = new ArrayList<>();

    public User(String username){
        super();
        this.username = username;
        users.add(this);
    }
    public User(String username, Double rating){
        this(username);
        this.rating = rating;
    }
    public User(String username, Date dateOfCreation) {
        super(dateOfCreation);
        this.username = username;
        users.add(this);
    }

    public User(int id, String username) {
        super(id);
        this.username = username;

        users.add(this);
    }
    public User(int id, Date dateOfCreation, String username) {
        super(id, dateOfCreation);
        this.username = username;
        this.rating = calculateRating();

        users.add(this);
    }
    public User(int id, Date dateOfCreation, String username, Double rating) {
        super(id, dateOfCreation);
        this.username = username;
        this.rating = rating;

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
        JdbcDao jdbcDao = (JdbcDao) new UserDaoFactory().getDao(DaoType.SQL);
        User user = (User) jdbcDao.getById(id);
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        User user = (User)obj;
        return this.getId() == user.getId() || getUsername().trim().equals(user.username.trim());
    }

    @Override
    public int hashCode() {
        return (super.hashCode() ^ username.hashCode());
    }
}
