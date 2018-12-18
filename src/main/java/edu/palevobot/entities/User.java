package edu.palevobot.entities;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.dao.user.DaoType;
import edu.palevobot.dao.user.UserDaoFactory;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends Base {
    @NotNull
    private String username;
    @NotNull
    private double rating;

    public static ArrayList<User> users = new ArrayList<>();

    public User(){
        //Oleg commit
        //Viktor commit is better
    }
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

    public void setRating(double rating) {
        this.rating = rating;
    }
}
