package edu.palevobot.entities;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.dao.palevo.PalevoDaoFactory;
import edu.palevobot.dao.user.DaoType;
import edu.palevobot.dao.user.UserDaoFactory;
import jdk.jshell.spi.ExecutionControl;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserPalevo extends Base{
    private int userId;
    private int palevoId;

    public static ArrayList<UserPalevo> userPalevos = new ArrayList<>();

    public UserPalevo(int id, User user, Palevo palevo) {
        super(id);
        userId = user.getId();
        palevoId = palevo.getId();
    }

    public User getUser() throws SQLException {
        if(userId == 0)
            return null;
        for (User user : User.users) {
            if(user.getId() == userId)
                return user;
        }

        JdbcDao jdbcDao = (JdbcDao) new UserDaoFactory().getDao(DaoType.SQL);
        User user = (User) jdbcDao.getById(userId);
        jdbcDao.closeConnection();
        return user;
    }

    public Palevo getPalevo() throws SQLException {
        if(palevoId == 0)
            return null;
        for (Palevo palevo : Palevo.palevos) {
            if(palevo.getId() == palevoId)
                return palevo;
        }
        JdbcDao jdbcDao = (JdbcDao) new PalevoDaoFactory().getDao(DaoType.SQL);
        Palevo palevo = (Palevo) jdbcDao.getById(palevoId);
        jdbcDao.closeConnection();
        return palevo;
    }

    public int getUserId() {
        return userId;
    }

    public int getPalevoId() {
        return palevoId;
    }
}
